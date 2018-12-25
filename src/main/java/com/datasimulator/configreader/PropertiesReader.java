package com.datasimulator.configreader;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


@Setter @Getter
public class PropertiesReader {

    private Properties properties;

    public PropertiesReader(String configFilePath) throws Exception{
        checkConfigFilePath(configFilePath);
        properties = new Properties();
        properties.load(new FileInputStream(new File(configFilePath)));
    }

    private boolean checkConfigFilePath(String configFilePath) throws Exception{
        return Files.exists(Paths.get(configFilePath));
    }

    public void mapPropertiesToBatchTemplateConfig(Properties properties){
        System.out.println("Reading properties file...");


    }
}
