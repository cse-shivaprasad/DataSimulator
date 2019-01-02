package com.datasimulator.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ComponentScan({"com.datasimulator.config"})
@Component
@Getter @Setter @ToString
@PropertySource("classpath:batchfeed_config.properties")
public class TemplateConfig {

    @Value("${datasimulator.batch.jobName}")
    private String jobName;

    @Value("${datasimulator.batch.outputDataFormat}")
    private String outputDataFormat;

    @Value("${datasimulator.batch.outputDelimiter}")
    private String outputDelimiter;

    @Value("${datasimulator.batch.outputFilePath}")
    private String outputFilePath;

    @Value("${datasimulator.batch.outputRepo}")
    private String outputRepo;

    @Value("${datasimulator.batch.targetConfigPath}")
    private String targetConfigPath;

    @Value("${datasimulator.batch.executionFrequency}")
    private String executionFrequency;

    @Value("${datasimulator.batch.recordCount}")
    private Long recordCount = 100l;

    @Value("${datasimulator.batch.locale}")
    private String locale="en-US";

    @Value("${datasimulator.batch.batchFieldConfigPath}")
    private String batchFieldConfigPath;

    //TODO : To decide between map and list for fieldConfig
    private Map<String,FieldConfig> fieldConfigMap = new TreeMap<String, FieldConfig>();

    private List<FieldConfig> fieldConfigList = new ArrayList<>();


}
