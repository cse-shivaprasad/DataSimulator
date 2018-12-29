package com.datasimulator.metadata;

import com.datasimulator.formatter.DataFormatter;
import com.datasimulator.writer.OutputWriter;
import lombok.Getter;

public enum RepoWriterType {

    //TODO : Add a method to configure new ReportWriterType dynamically using reflections.
    LOCAL_FILE("LOCAL_FILE","com.datasimulator.writer.FileWriter"),

    CONSOLE("CONSOLE","com.datasimulator.writer.ConsoleWriter"),

    SQL_DB("SQL_DB","com.datasimulator.writer.SqlWriter"),

    KAFKA("KAFKA","com.datasimulator.writer.KafkaWriter");

    private String repoType;

    private String writerClassName;

    private RepoWriterType(String repoType, String writerClassName){
        this.repoType = repoType;
        this.writerClassName = writerClassName;
    }

    public OutputWriter getWriterInstance() throws Exception{
        return (OutputWriter) Class.forName(writerClassName).newInstance();
    }
}
