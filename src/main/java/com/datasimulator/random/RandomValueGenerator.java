package com.datasimulator.random;

import com.datasimulator.config.BatchFieldConfig;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;

public class RandomValueGenerator {

    private static RandomValueGenerator valueGenerator = new RandomValueGenerator();
    //TODO : Locale to be parameterized
    private static Faker faker = new Faker(Locale.US);
    private static RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    public RandomValueGenerator(){
    }

    public static RandomValueGenerator getRandomValueGenerator(){
        return valueGenerator;
    }

    //TODO : To optimize method signature as it can be utilized between Batch and Streaming
    public String getRandomLongValue(BatchFieldConfig fieldConfig) throws Exception{
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Long minValue = valueRange.getLong("MIN_VALUE");
        Long maxValue = valueRange.getLong("MAX_VALUE");
        return randomDataGenerator.nextLong(minValue,maxValue)+"";
    }

    public String getRandomIntValue(BatchFieldConfig fieldConfig) throws Exception {
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Integer minValue = valueRange.getInt("MIN_VALUE");
        Integer maxValue = valueRange.getInt("MAX_VALUE");
        return randomDataGenerator.nextLong(minValue,maxValue)+"";
    }

    public String getRandomDoubleValue(BatchFieldConfig fieldConfig) throws Exception {
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Double minValue = valueRange.getDouble("MIN_VALUE");
        Double maxValue = valueRange.getDouble("MAX_VALUE");
        return randomDataGenerator.nextUniform(minValue,maxValue)+"";
    }

    public String getRandomStringValue(BatchFieldConfig fieldConfig) throws Exception {
        JSONObject possibleValues = new JSONObject(fieldConfig.getFieldValueRange());
        JSONArray arrayValues = possibleValues.getJSONArray("POSSIBLE_VALUES");
        Integer randomInt = randomDataGenerator.nextInt(0,arrayValues.length());
        return arrayValues.getString(randomInt);
    }

    public String getName(BatchFieldConfig fieldConfig){
        return faker.name().name();
    }

    public String getMasterCardNumber(BatchFieldConfig fieldConfig){
        return faker.finance().creditCard(CreditCardType.MASTERCARD);
    }

    public String getEmail(BatchFieldConfig fieldConfig){
        return faker.internet().emailAddress();
    }

    public String getPhone(BatchFieldConfig fieldConfig){
        return faker.phoneNumber().cellPhone();
    }

    public String getAddress(BatchFieldConfig fieldConfig){
        return faker.address().fullAddress();
    }

    public String getCity(BatchFieldConfig fieldConfig){
        return faker.address().city();
    }

    public String getState(BatchFieldConfig fieldConfig){
        return faker.address().state();
    }

    public String getCountry(BatchFieldConfig fieldConfig){
        return faker.address().state();
    }

    public String getZipCode(BatchFieldConfig fieldConfig){
        return faker.address().zipCode();
    }
}
