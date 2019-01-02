package com.datasimulator.orchestrator;

import com.datasimulator.config.TemplateConfig;
import com.datasimulator.processor.BatchSimulationProcessor;
import com.datasimulator.processor.SimulationProcessor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter @Getter
public class BatchSimulationOrchestrator {

    @Autowired
    private TemplateConfig templateConfig;

    public void orchestrateBatchSimulationProcess(String configFile) throws Exception{
        //TODO : Optimize below code using functional composition or builder pattern
        SimulationProcessor processor = new BatchSimulationProcessor(templateConfig)
                                                .preProcesDataSimulationConfig(templateConfig)
                                                .processDataSimulation(templateConfig)
                                                .postProcess();
    }
}
