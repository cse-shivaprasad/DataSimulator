package com.datasimulator.processor;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.config.TemplateConfig;
import com.datasimulator.formatter.DataFormatter;
import com.datasimulator.metadata.CustomFieldType;
import com.datasimulator.metadata.OutputFormatType;
import com.datasimulator.metadata.RepoWriterType;
import com.datasimulator.writer.OutputWriter;
import com.semihunaldi.excelorm.ExcelReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//TODO : Optimize Generics parameterization at class level
public class BatchSimulationProcessor implements SimulationProcessor{

    private Logger logger = Logger.getLogger(BatchSimulationProcessor.class);

    @Value("${configPath}")
    private String configPath;
    private TemplateConfig templateConfig;
    private DataFormatter dataFormatter;
    private OutputWriter outputWriter;

    public BatchSimulationProcessor(TemplateConfig templateConfig){
        this.templateConfig = templateConfig;
    }

    @Override
    public BatchSimulationProcessor preProcesDataSimulationConfig(TemplateConfig templateConfig) throws Exception{

        readFieldConfigAndMapToTemplateConfig(templateConfig);
        dataFormatter = OutputFormatType.valueOf(templateConfig.getOutputDataFormat()).getFormatterInstance();
        outputWriter = RepoWriterType.valueOf(templateConfig.getOutputRepo()).getWriterInstance();
        outputWriter.initializeResource(templateConfig.getOutputConfigPath());
        return  this;
    }

    @Override
    public SimulationProcessor processDataSimulation(TemplateConfig templateConfig) throws Exception{

       //TODO : Chaining of below functionality using Functional Style
        //TODO : Keep the code optimized to not to depend on record count
        //TODO : Apply CompletableFuture/Reactive approach here.
        for(long count=1 ; count <= templateConfig.getRecordCount(); count++){

            generateRandomContent(templateConfig);

            String formattedRowContent = dataFormatter.formatData(templateConfig.getFieldConfigList());

            outputWriter.writeOutputContent(formattedRowContent);
        }
        return this;
    }

    @Override
    public SimulationProcessor postProcess() throws Exception{
        outputWriter.closeResource();
        return this;
    }

    public void readFieldConfigAndMapToTemplateConfig(TemplateConfig templateConfig) throws Exception{

        File file = new File(templateConfig.getBatchFieldConfigPath());

        List<FieldConfig> fieldConfigList = new ExcelReader().read(file, FieldConfig.class);

        //TemplateConfig is getting mutated. Code to be optimized to make it immutable.
        templateConfig.setFieldConfigList(fieldConfigList);

        logger.info("Template Config Object Post Read :" + templateConfig);
    }

    private void generateRandomContent(TemplateConfig templateConfig) {
        for(FieldConfig fieldConfig : templateConfig.getFieldConfigList()){
            fieldConfig.setGeneratedValue(getFieldValue(fieldConfig,templateConfig.getLocale()));
        }
    }

    private String getFieldValue(FieldConfig fieldConfig, String locale) {
        return   CustomFieldType.valueOf(fieldConfig.getCustomDataType())
                                                .getGeneratorFunction()
                                                .apply(fieldConfig);
    }

    private TemplateConfig mapFieldConfigToBatchTemplate(List<FieldConfig> batchFieldConfigList){

        Map<String, FieldConfig> fieldConfigMap = batchFieldConfigList.stream()
                                                            .collect(Collectors.toMap(FieldConfig::getFieldName,
                                                                        fieldConfig -> fieldConfig));
        templateConfig.setFieldConfigMap(fieldConfigMap);
        return  templateConfig;
    }
}
