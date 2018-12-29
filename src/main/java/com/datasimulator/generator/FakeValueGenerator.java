package com.datasimulator.generator;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.metadata.CustomFieldType;
import com.datasimulator.metadata.RangeMetaData;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.util.*;
import java.util.function.Function;

public class FakeValueGenerator {

    //TODO : Locale to be parameterized/optimized
    private static Faker defaultFaker = new Faker(Locale.US);

    private static Map<String, Faker> localeFakerMap = new HashMap();

    private static Function<FieldConfig,Faker> fakerFunc = fieldConfig -> getFaker(fieldConfig.getLocale());

    public static String getName(FieldConfig fieldConfig){
        Function<Faker,String> getNameFunc = faker -> getFaker(fieldConfig.getLocale()).name().name();
        return RandomValueGenerator.generateFieldValue(fieldConfig,fakerFunc.andThen(getNameFunc));
    }

    public static String getCreditCardNumber(FieldConfig fieldConfig){
        String fakerCardType = CustomFieldType.valueOf(fieldConfig.getCustomDataType()).getFieldName();
        CreditCardType cardType = CreditCardType.valueOf(fakerCardType);
        Function<Faker,String> getMasterCardNumberFunc =  faker -> faker.finance().creditCard(cardType);
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(getMasterCardNumberFunc));
    }

    //Generate Email Based on pattern. Eg: *****.gmail.com, ****.yahoo.com, etc
    public static String getPatternBasedEmail(FieldConfig fieldConfig) throws Exception{
        JSONObject pattern = new JSONObject(fieldConfig.getFieldValueRange());
        String emailPattern = pattern.getString(RangeMetaData.PATTERN.name());
        Function<Faker,String> getEmailFunc = faker -> faker.bothify(emailPattern);
        return RandomValueGenerator.generateFieldValue(fieldConfig,fakerFunc.andThen(getEmailFunc));
    }

    public static String getEmail(FieldConfig fieldConfig) {
        Function<Faker,String> getEmailFunc = faker -> faker.internet().emailAddress();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(getEmailFunc));
    }

    public static String getPhone(FieldConfig fieldConfig){
        Function<Faker,String> phoneFunc = faker -> faker.phoneNumber().cellPhone();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(phoneFunc));
    }

    public static String getAddress(FieldConfig fieldConfig){
        Function<Faker,String> addressFunc = faker -> faker.address().fullAddress();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(addressFunc));
    }

    public static String getCity(FieldConfig fieldConfig){
        Function<Faker,String> cityFunc = faker -> faker.address().city();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(cityFunc));
    }

    public static String getState(FieldConfig fieldConfig){
        Function<Faker,String> stateFunc = faker -> faker.address().state();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(stateFunc));
    }

    public static String getCountry(FieldConfig fieldConfig){
        Function<Faker,String> countryFunc = faker -> faker.address().country();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(countryFunc));
    }

    public static String getZipCode(FieldConfig fieldConfig){
        Function<Faker,String> zipCodeFunc = faker -> faker.address().zipCode();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(zipCodeFunc));
    }

    public static String getCurrencyCode(FieldConfig fieldConfig){
        Function<Faker,String> currencyCodeFunc = faker -> faker.currency().code();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(currencyCodeFunc));
    }

    public static String getCurrencyName(FieldConfig fieldConfig){
        Function<Faker,String> currencyNameFunc = faker -> faker.currency().name();
        return RandomValueGenerator.generateFieldValue(fieldConfig, fakerFunc.andThen(currencyNameFunc));
    }

    public static Faker getFaker(String locale){
        if(Objects.isNull(locale) || locale.trim().length() ==0)
            return defaultFaker;

        if(!localeFakerMap.containsKey(locale)){
            Faker newFaker = new Faker(new Locale(locale));
            localeFakerMap.put(locale,newFaker);
        }
        return localeFakerMap.get(locale);
    }
}
