package com.datasimulator.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Service
@Getter @Setter @ToString
public class BatchTemplateConfig {

    @Value("${datasimulator.batch.jobName}")
    private String jobName;

    @Value("${datasimulator.batch.outputDataFormat}")
    private String outputDataFormat;

    @Value("${datasimulator.batch.outputDelimiter}")
    private String outputDelimiter;

    @Value("${datasimulator.batch.outputConfigPath}")
    private String outputConfigPath;

    @Value("${datasimulator.batch.recordCount}")
    private String outputRepo;

    @Value("${datasimulator.batch.recordCount}")
    private String executionFrequency;

    @Value("${datasimulator.batch.recordCount}")
    private String recordCount;

    @Value("${datasimulator.batch.locale}")
    private String locale;

    @Value("${datasimulator.batch.batchFieldConfigPath}")
    private String batchFieldConfigPath;


    private Map<String,BatchFieldConfig> fieldConfigMap = new TreeMap<String, BatchFieldConfig>();
    private List<BatchFieldConfig> fieldConfigList = new ArrayList<>();


}
