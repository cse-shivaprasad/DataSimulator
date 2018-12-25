package com.datasimulator.orchestrator;

import com.datasimulator.config.BatchTemplateConfig;
import com.datasimulator.processor.BatchSimulationProcessor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter @Setter
public class BatchSimulationOrchestrator {

    @Autowired
    private BatchTemplateConfig templateConfig;

    //private final BatchSimulationProcessor processor = new BatchSimulationProcessor();

    public void orchestrateBatchSimulationProcess(String configFile) throws Exception{

        BatchSimulationProcessor processor = new BatchSimulationProcessor(templateConfig);
        BatchTemplateConfig templateConfig = processor.preProcesDataSimulationConfig(this.templateConfig);
        processor.processDataSimulation(templateConfig);
        //processor.postProcess();
    }
}
