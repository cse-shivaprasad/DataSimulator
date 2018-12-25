package com.datasimulator.driver;

import com.datasimulator.orchestrator.BatchSimulationOrchestrator;

public class BatchDataSimulationDriver {

    public static void main(String[] args) throws Exception{
        checkInputArguments(args);
        new BatchSimulationOrchestrator().orchestrateBatchSimulationProcess(args[0].toString());
    }

    private static void checkInputArguments(String[] args){
        if (args.length != 1) {
            System.err.println("Argument Count Mismatch. Exiting the process");
            throw new IllegalArgumentException();
        }
    }
}
