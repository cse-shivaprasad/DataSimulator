package com.datasimulator.writer;

public class ConsoleWriter  implements OutputWriter<String,String>{

    @Override
    public void initializeResource(String config) throws Exception {

        // As it's a console writing there is no initialization involved.
    }
    @Override
    public void writeOutputContent(String outputContent){
        System.out.println(outputContent);
    }

    @Override
    public void closeResource() {

        // As it's a console writing there is no closing of resources involved.
    }
}
