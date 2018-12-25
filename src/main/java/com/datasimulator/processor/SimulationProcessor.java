package com.datasimulator.processor;

public interface SimulationProcessor<PRE_PROCESS_INPUT,PRE_PROCESS_OUTPUT,PROCESSING_INPUT,DATAPROCESSING_OUTPUT,POSTPROCESS_INPUT> {

    public PRE_PROCESS_OUTPUT preProcesDataSimulationConfig(PRE_PROCESS_INPUT preprocessInput) throws Exception;

    public DATAPROCESSING_OUTPUT processDataSimulation(PROCESSING_INPUT processingInput) throws Exception;

    public void postProcess(POSTPROCESS_INPUT postprocessInput);
}
