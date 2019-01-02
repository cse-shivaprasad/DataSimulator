package com.datasimulator.formatter;

import com.datasimulator.config.FieldConfig;

import java.util.List;
import java.util.stream.Collectors;

public class CSVDataFormatter implements DataFormatter<List<FieldConfig>> {

    public String formatData(List<FieldConfig> inputconfig){

        //TODO : To generalize this class for all delimited text content. "," to be optimized
        return inputconfig.stream()
                .map(fieldConfig -> fieldConfig.getGeneratedValue())
                .collect(Collectors.joining(","));
    }
}
