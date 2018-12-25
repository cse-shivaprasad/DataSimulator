package com.datasimulator.processor;

import com.datasimulator.config.BatchFieldConfig;
import com.datasimulator.config.BatchTemplateConfig;
import com.datastax.driver.core.querybuilder.Batch;
import com.semihunaldi.excelorm.ExcelReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
//TODO : Optimize Generics parameterization at class level
public class BatchSimulationProcessor implements SimulationProcessor<BatchTemplateConfig,BatchTemplateConfig,BatchTemplateConfig,Boolean,String> {

    @Value("${configPath}")
    private String configPath;

    private BatchTemplateConfig templateConfig;

    public BatchSimulationProcessor(BatchTemplateConfig templateConfig){
        this.templateConfig = templateConfig;
    }

    @Override
    public BatchTemplateConfig preProcesDataSimulationConfig(BatchTemplateConfig templateConfig) throws Exception{

        ExcelReader excelReader = new ExcelReader();
        File file = new File(templateConfig.getBatchFieldConfigPath());
        List<BatchFieldConfig> fieldConfigList = excelReader.read(file, BatchFieldConfig.class);
        //TemplateConfig is getting mutated. Code to be optimized to make it immutable.
        templateConfig = mapFieldConfigToBatchTemplate(fieldConfigList);
        return  templateConfig;
    }

    @Override
    public Boolean processDataSimulation(BatchTemplateConfig templateConfig) {

        for(BatchFieldConfig fieldConfig : templateConfig.getFieldConfigList()){
        }
        return null;
    }

    @Override
    public void postProcess(String o) {

    }

    private BatchTemplateConfig mapFieldConfigToBatchTemplate(List<BatchFieldConfig> batchFieldConfigList){

        Map<String, BatchFieldConfig> fieldConfigMap = batchFieldConfigList.stream()
                                                            .collect(Collectors.toMap(BatchFieldConfig::getFieldName,
                                                                        fieldConfig -> fieldConfig));
        templateConfig.setFieldConfigMap(fieldConfigMap);
        return  templateConfig;

    }
}
