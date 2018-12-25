package com.datasimulator.constant;

public interface DataSimulatorPropertiesConst {
/*
    datasimulator.batch.jobName=CustomerBatchFeed

#Supported output data formats are FIXED_WIDTH,JSON,XML
    datasimulator.batch.outputDataFormat=FIXED_WIDTH

#Supported delimiters are COMMA,TAB,SPACE
    datasimulator.batch.outputDelimiter=NA

#Supported outputRepo are LOCAL_FILE, KAFKA, MONGO_DB, HDFS
    datasimulator.batch.outputRepo=LOCAL_FILE

#outputConfigPath holds config file path which stores target connectivity detail such as Kafka, HDFS, LocalFilePath, RDBMS, Mongo etc...
    datasimulator.batch.outputConfigPath=/Users/Shivaprasad/Documents/workspace_java/DataSimulator/src/main/resources/config/customer_batch_transactions/directory

#executionFrequency is in seconds
    datasimulator.batch.executionFrequency=10

            #totalRecodCount expected from the simulator execution
    datasimulator.batch.recordCount=100

            #locale standard Java/Scala supported locale names
    datasimulator.batch.locale=en-US
*/

    public String JOB_NAME = "datasimulator.batch.jobName";
    public String OUTPUT_DATAFORMAT = "datasimulator.batch.outputDataFormat";
    public String OUTPUT_DELIMITER = "datasimulator.batch.outputDelimiter";
    public String OUTPUT_REPO = "datasimulator.batch.outputRepo";
    public String OUTPUT_CONFIGPATH = "datasimulator.batch.outputConfigPath";
    public String EXECUTION_FREQUENCY = "datasimulator.batch.executionFrequency";
    public String RECORD_COUNT = "datasimulator.batch.recordCount";
    public String LOCALE = "datasimulator.batch.locale";
    public String FIELD_TEMPLATE_CONFIGPATH = "datasimulator.batch.fieldConfigPath";

}
