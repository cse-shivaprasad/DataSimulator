package com.datasimulator.metamodel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder
public class DBConfig {

    private String hostName;
    private Integer port;
    private String dbName;
    private String tableName;
}
