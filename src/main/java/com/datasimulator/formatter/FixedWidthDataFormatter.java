package com.datasimulator.formatter;

import com.datasimulator.config.FieldConfig;

import java.util.List;

public class FixedWidthDataFormatter implements DataFormatter<List<FieldConfig>> {

    private String PADDING_STRING_TXT = " ";

    public String formatData(List<FieldConfig> inputconfig) throws Exception{
        // Ordering of columns is very important
        // Padding of the text for the fields which are lesser than field length will be padded by spaces
        //TODO : Optimize the below code with Streams and Other Libraries

       StringBuilder rowBuilder = new StringBuilder();
       for(FieldConfig fieldConfig :inputconfig){
           rowBuilder.append(getFieldFixedWidthContent(fieldConfig));
       }
       return rowBuilder.toString();
    }

    private String getFieldFixedWidthContent(FieldConfig fieldConfig){
        StringBuilder fieldContent = new StringBuilder(fieldConfig.getGeneratedValue());
        while(fieldConfig.getFieldLength() >= fieldContent.length()){
            fieldContent.append(PADDING_STRING_TXT);
        }
        return fieldContent.toString();
    }
}
