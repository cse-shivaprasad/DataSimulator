package com.datasimulator.metadata;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum OutputFormatType {

    CSV("CSV","com.datasimulator.formatter.CSVDataFormatter"),

    JSON("JSON","com.datasimulator.formatter.JsonDataFormatter"),

    XML("XML","com.datasimulator.formatter.XMLDataFormatter");

    private String outputFormat;

    private String formatterClassName;

    private OutputFormatType(String outputFormat, String formatterClassName){
        this.outputFormat = outputFormat;
        this.formatterClassName = formatterClassName;
    }
}
