package com.datasimulator.processor;

import com.datasimulator.config.TemplateConfig;

public interface SimulationProcessor {

    public SimulationProcessor preProcesDataSimulationConfig(TemplateConfig preprocessInput) throws Exception;

    public SimulationProcessor processDataSimulation(TemplateConfig processingInput) throws Exception;

    public SimulationProcessor postProcess() throws Exception;
}
