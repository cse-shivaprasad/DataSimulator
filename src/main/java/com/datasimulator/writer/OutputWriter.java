package com.datasimulator.writer;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.formatter.DataFormatter;

import java.util.List;

public interface OutputWriter<T> {

    public void initializeResource(T resource) throws Exception;

    public void writeOutputContent(DataFormatter formatter, List<FieldConfig> fieldValues) throws Exception;

    public void closeResource() throws  Exception;
}
