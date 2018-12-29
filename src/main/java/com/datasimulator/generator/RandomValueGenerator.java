package com.datasimulator.generator;

import com.datasimulator.config.FieldConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RandomValueGenerator {

    private static Map<String,List<String>> uniqueValuesMap = new HashMap<>();

    public static String generateFieldValue(FieldConfig fieldConfig, Function<FieldConfig,String> genFunction){

        String randomValue = genFunction.apply(fieldConfig);
        String isUniqueValue = fieldConfig.getIsUnique();
        if(isUniqueValue != null && isUniqueValue.equalsIgnoreCase("Y")){
            if(!uniqueValuesMap.containsKey(fieldConfig.getFieldName())) {
                uniqueValuesMap.put(fieldConfig.getFieldName(), new ArrayList<>());
                uniqueValuesMap.get(fieldConfig.getFieldName()).add(randomValue);
                return randomValue;
            }
            while(uniqueValuesMap.get(fieldConfig.getFieldName()).contains(randomValue)){
                randomValue = genFunction.apply(fieldConfig);
            }
            uniqueValuesMap.get(fieldConfig.getFieldName()).add(randomValue);
        }
        return randomValue;
    }
}
