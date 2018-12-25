package com.datasimulator.poc.spring;

import com.datasimulator.config.BatchTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:batchconfig.properties.batchfeed_config.properties")
public class PropertiesEnvironment {

    @Autowired
    private BatchTemplateConfig batchTemplateConfig;

    @Bean
    public void getBatchFieldConfig(){
        System.out.println("Job Name "+batchTemplateConfig.getJobName());
    }
}
