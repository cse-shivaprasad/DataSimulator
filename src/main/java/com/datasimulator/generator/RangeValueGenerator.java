package com.datasimulator.generator;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.metadata.RangeMetaData;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


public class RangeValueGenerator {

    private  RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    private static Map<String,List<String>> uniqueValuesMap = new HashMap<>();

    //TODO : To optimize method signature as it can be utilized between Batch and Streaming
    //TODO : To implement Monads to avoid throwing Exception
    public String getRandomLongValue(FieldConfig fieldConfig) throws Exception{
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Long minValue = valueRange.getLong(RangeMetaData.MIN_VALUE.name());
        Long maxValue = valueRange.getLong(RangeMetaData.MAX_VALUE.name());
        return generateUniqueFieldValue(fieldConfig,minValue,maxValue,getNextLongValue);
    }

    public String getRandomIntValue(FieldConfig fieldConfig) throws Exception {
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Integer minValue = valueRange.getInt(RangeMetaData.MIN_VALUE.name());
        Integer maxValue = valueRange.getInt(RangeMetaData.MAX_VALUE.name());
        return generateUniqueFieldValue(fieldConfig,minValue,maxValue,getNextIntValue);
    }

    public String getRandomDoubleValue(FieldConfig fieldConfig) throws Exception {
        JSONObject valueRange = new JSONObject(fieldConfig.getFieldValueRange());
        Double minValue = valueRange.getDouble(RangeMetaData.MIN_VALUE.name());
        Double maxValue = valueRange.getDouble(RangeMetaData.MAX_VALUE.name());
        return generateUniqueFieldValue(fieldConfig,minValue,maxValue,getNextDoubleValue);
    }

    public String getRandomRangeStringValue(FieldConfig fieldConfig) throws Exception {
        JSONObject possibleValues = new JSONObject(fieldConfig.getFieldValueRange());
        JSONArray arrayValues = possibleValues.getJSONArray(RangeMetaData.POSSIBLE_VALUES.name());
        Integer randomInt = randomDataGenerator.nextInt(0,arrayValues.length()-1);
        return arrayValues.getString(randomInt);
    }

    public BiFunction<Long,Long,String> getNextLongValue = (min,max) -> randomDataGenerator.nextLong(min,max)+"";
    public BiFunction<Integer,Integer,String> getNextIntValue = (min,max) -> randomDataGenerator.nextInt(min,max)+"";
    public BiFunction<Double,Double,String> getNextDoubleValue = (min,max) -> randomDataGenerator.nextUniform(min,max)+"";

    private <T extends Number> String generateUniqueFieldValue(FieldConfig fieldConfig, T min, T max,
                                                              BiFunction<T, T, String> genFunction) {
        String randomValue = genFunction.apply(min, max);
        if (isNotUniqueField(fieldConfig)){
            return randomValue;
        }
        if(!uniqueValuesMap.containsKey(fieldConfig.getFieldName())){
            uniqueValuesMap.put(fieldConfig.getFieldName(), new ArrayList<>());
            uniqueValuesMap.get(fieldConfig.getFieldName()).add(randomValue);
            return randomValue;
        }
        while (uniqueValuesMap.get(fieldConfig.getFieldName()).contains(randomValue)) {
            randomValue = genFunction.apply(min,max);
        }
        uniqueValuesMap.get(fieldConfig.getFieldName()).add(randomValue);

        return randomValue;
    }

    private boolean isNotUniqueField(FieldConfig fieldConfig){
        return Objects.isNull(fieldConfig.getIsUnique()) || fieldConfig.getIsUnique().equalsIgnoreCase("N");
    }

    private boolean isFieldNotAddedToMap(String fieldName){
        return !uniqueValuesMap.containsKey(fieldName);
    }

    private boolean addRandomFieldAndValueToMap(String fieldName, String randomValue){
        uniqueValuesMap.put(fieldName, new ArrayList<>());
        return uniqueValuesMap.get(fieldName).add(randomValue);
    }

}
