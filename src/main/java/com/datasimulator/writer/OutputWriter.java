package com.datasimulator.writer;

public interface OutputWriter<T,U> {

    public void initializeResource(T resource) throws Exception;

    public void writeOutputContent(U outputContent) throws Exception;

    public void closeResource() throws  Exception;
}
