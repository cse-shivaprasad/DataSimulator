package com.datasimulator.metadata;

import lombok.Getter;

@Getter
public enum RepoWriterType {

    FILE("FILE","com.datasimulator.writer.FileStreamWriter"),

    CONSOLE("CONSOLE","com.datasimulator.writer.ConsoleStreamWriter"),

    SQL_DB("SQL_DB","com.datasimulator.writer.SqlStreamWriter"),

    KAFKA("KAFKA","com.datasimulator.writer.KafkaWriter");

    private String repoType;

    private String writerClassName;

    private RepoWriterType(String repoType, String writerClassName){
        this.repoType = repoType;
        this.writerClassName = writerClassName;
    }
}
