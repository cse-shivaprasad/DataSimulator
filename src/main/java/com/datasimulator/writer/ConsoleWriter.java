package com.datasimulator.writer;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.formatter.DataFormatter;

import java.util.List;

public class ConsoleWriter  implements OutputWriter<String>{

    @Override
    public void initializeResource(String config) throws Exception {

        // As it's a console writing there is no initialization involved.
    }
    @Override
    public void writeOutputContent(DataFormatter formatter, List<FieldConfig> fieldValues) throws Exception{
        System.out.println(formatter.formatData(fieldValues));
    }

    @Override
    public void closeResource() {

        // As it's a console writing there is no closing of resources involved.
    }
}
