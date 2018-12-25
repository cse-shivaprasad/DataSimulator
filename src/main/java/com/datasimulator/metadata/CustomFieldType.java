package com.datasimulator.metadata;

//TODO: To validate whether to replace it with interface

import com.datasimulator.config.BatchFieldConfig;
import com.datasimulator.random.RandomValueGenerator;
import lombok.Getter;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.jooq.lambda.Unchecked;

import java.util.function.Function;

@Getter
public enum CustomFieldType {

    //TODO : Split the randomvalue generation from different classes and keeping this approach extensible
    RANDOM_LONG("RANDOM_LONG", Unchecked.function(new RandomValueGenerator()::getRandomLongValue)),
    RANDOM_DOUBLE("RANDOM_DOUBLE",Unchecked.function(new RandomValueGenerator()::getRandomDoubleValue)),
    RANDOM_STRING("RANDOM_STRING",Unchecked.function(new RandomValueGenerator()::getRandomStringValue)),
    NAME("NAME", new RandomValueGenerator()::getName),
    ACCOUNT_NUMBER("ACCOUNT_NUMBER", Unchecked.function(new RandomValueGenerator()::getRandomLongValue)),
    MASTER_CARD_NUMBER("MASTER_CARD_NUMBER", new RandomValueGenerator()::getMasterCardNumber),
    EMAIL("EMAIL",new RandomValueGenerator()::getEmail),
    PHONE("PHONE",new RandomValueGenerator()::getPhone),
    ADDRESS("ADDRESS",new RandomValueGenerator()::getAddress),
    CITY("CITY",new RandomValueGenerator()::getCity),
    STATE("STATE",new RandomValueGenerator()::getState),
    COUNTRY("COUNTRY",new RandomValueGenerator()::getCountry),
    ZIPCODE("ZIPCODE",new RandomValueGenerator()::getZipCode);


    private String fieldName;
    private Function<BatchFieldConfig,String> generatorFunction;
    private RandomValueGenerator randomValueGenerator = new RandomValueGenerator();

    private CustomFieldType(String fieldName, Function<BatchFieldConfig,String> generatorFunction){
        this.fieldName = fieldName;
        this.generatorFunction = generatorFunction;
    }

}
