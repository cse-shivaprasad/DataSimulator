package com.datasimulator.metadata;

//TODO: To validate whether to replace it with interface

import com.datasimulator.config.FieldConfig;
import com.datasimulator.generator.FakeValueGenerator;
import com.datasimulator.generator.RangeValueGenerator;
import lombok.Getter;
import org.jooq.lambda.Unchecked;

import java.util.function.Function;

@Getter
public enum CustomFieldType {

    //TODO : Split the randomvalue generation from different classes and keeping this approach extensible
    RANDOM_LONG("RANDOM_LONG", Unchecked.function(new RangeValueGenerator()::getRandomLongValue)),
    RANDOM_DOUBLE("RANDOM_DOUBLE",Unchecked.function(new RangeValueGenerator()::getRandomDoubleValue)),
    RANDOM_STRING("RANDOM_STRING",Unchecked.function(new RangeValueGenerator()::getRandomRangeStringValue)),
    ACCOUNT_NUMBER("ACCOUNT_NUMBER", Unchecked.function(new RangeValueGenerator()::getRandomLongValue)),

    NAME("NAME", FakeValueGenerator::getName),
    EMAIL("EMAIL",FakeValueGenerator::getEmail),
    PATTERN_EMAIL("PATTERN_EMAIL",Unchecked.function(FakeValueGenerator::getPatternBasedEmail)),
    PHONE("PHONE",FakeValueGenerator::getPhone),
    ADDRESS("ADDRESS",FakeValueGenerator::getAddress),
    CITY("CITY",FakeValueGenerator::getCity),
    STATE("STATE",FakeValueGenerator::getState),
    COUNTRY("COUNTRY",FakeValueGenerator::getCountry),
    ZIPCODE("ZIPCODE",FakeValueGenerator::getZipCode),

    MASTER_CARD_NUMBER("MASTERCARD", FakeValueGenerator::getCreditCardNumber),
    VISA_CARD_NUMBER("VISA", FakeValueGenerator::getCreditCardNumber),
    DISCOVER_CARD_NUMBER("DISCOVER", FakeValueGenerator::getCreditCardNumber),
    AMEX_CARD_NUMBER("AMERICAN_EXPRESS", FakeValueGenerator::getCreditCardNumber),

    CURRENCY_CODE("CURRENCY_CODE", FakeValueGenerator::getCurrencyCode),
    CURRENCY_NAME("CURRENCY_NAME", FakeValueGenerator::getCurrencyName);

    private String fieldName;
    private Function<FieldConfig,String> generatorFunction;

    private CustomFieldType(String fieldName, Function<FieldConfig,String> generatorFunction){
        this.fieldName = fieldName;
        this.generatorFunction = generatorFunction;
    }

}
