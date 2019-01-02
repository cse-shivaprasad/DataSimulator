package com.datasimulator.writer;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.config.TemplateConfig;
import com.datasimulator.formatter.DataFormatter;
import com.datasimulator.metamodel.DBConfig;
import org.apache.metamodel.UpdateSummary;
import org.apache.metamodel.UpdateableDataContext;
import org.apache.metamodel.create.CreateTable;
import org.apache.metamodel.insert.InsertInto;
import org.apache.metamodel.schema.Table;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

//TODO : To modify code to write to SQL DBs
public class SqlDBWriter implements OutputWriter<TemplateConfig> {

    private UpdateableDataContext dataContext;
    private DBConfig sqlDBConfig;
    private Table table;

    @Override
    public void initializeResource(TemplateConfig templateConfig) throws Exception {
        loadDBPropertiesToMongoConfig(templateConfig.getTargetConfigPath());
        setDBContext();
        createTableIfNotExists(sqlDBConfig.getTableName(),templateConfig);
        table = dataContext.getSchemaByName(sqlDBConfig.getDbName()).getTableByName(sqlDBConfig.getTableName());
    }

    private void setDBContext() {

        //TODO : To provide implementation for SQL DB Writes
        //MongoClient client = new MongoClient(sqlDBConfig.getHostName(),sqlDBConfig.getPort());
        //MongoDatabase mongoDB = client.getDatabase(sqlDBConfig.getDbName());
        //dataContext = new MongoDbDataContext(mongoDB);
    }

    @Override
    public void writeOutputContent(DataFormatter formatter, List<FieldConfig> fieldConfigList) throws Exception {

        InsertInto insertInto = new InsertInto(table);
        for(FieldConfig fieldConfig : fieldConfigList){
            insertInto.value(fieldConfig.getFieldName(),fieldConfig.getGeneratedValue());
        }
        dataContext.executeUpdate(insertInto);
        //return null;
    }

    @Override
    public void closeResource() throws Exception {

    }

    private void loadDBPropertiesToMongoConfig(String resoucePath) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(resoucePath)));
        sqlDBConfig = DBConfig.builder()
                .hostName(properties.getProperty("host"))
                .port(Integer.parseInt(properties.getProperty("port")))
                .dbName(properties.getProperty("db"))
                .tableName(properties.getProperty("table"))
                .build();
    }

    private Optional<Integer> createTableIfNotExists(String tableName, TemplateConfig templateConfig){

        if(tableExistsAlready(tableName))
            return Optional.of(1);

        CreateTable createTable = new CreateTable(sqlDBConfig.getDbName(), sqlDBConfig.getTableName());
        for(FieldConfig fieldConfig : templateConfig.getFieldConfigList()){
            createTable.withColumn(fieldConfig.getFieldName());
        }
        UpdateSummary summary = dataContext.executeUpdate(createTable);
        return summary.getUpdatedRows();
    }

    private boolean tableExistsAlready(String tableName) {
        return dataContext.getSchemaByName(sqlDBConfig.getDbName()).getTableNames().contains(tableName);
    }
}
