package com.datasimulator.writer;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.config.TemplateConfig;
import com.datasimulator.formatter.DataFormatter;
import com.datasimulator.metamodel.DBConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class MongoDBWriter implements OutputWriter<TemplateConfig> {

    private DBConfig dbConfig;
    private MongoCollection mongoCollection;

    @Override
    public void initializeResource(TemplateConfig templateConfig) throws Exception {
        loadDBPropertiesToMongoConfig(templateConfig.getTargetConfigPath());
        setMongoDBContext();
    }

    private void setMongoDBContext() {
        MongoClient client = new MongoClient(dbConfig.getHostName(), dbConfig.getPort());
        MongoDatabase mongoDB = client.getDatabase(dbConfig.getDbName());
        mongoCollection = mongoDB.getCollection(dbConfig.getTableName());

    }

    @Override
    public void writeOutputContent(DataFormatter formatter, List<FieldConfig> fieldConfigList) throws Exception {
        Document document = new Document();
        for(FieldConfig fieldConfig : fieldConfigList){
            document.put(fieldConfig.getFieldName(),fieldConfig.getGeneratedValue());
        }
        mongoCollection.insertOne(document);
    }

    @Override
    public void closeResource() throws Exception {

    }

    private void loadDBPropertiesToMongoConfig(String resoucePath) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(resoucePath)));
        dbConfig = DBConfig.builder()
                .hostName(properties.getProperty("host"))
                .port(Integer.parseInt(properties.getProperty("port")))
                .dbName(properties.getProperty("db"))
                .tableName(properties.getProperty("table"))
                .build();
    }


}
