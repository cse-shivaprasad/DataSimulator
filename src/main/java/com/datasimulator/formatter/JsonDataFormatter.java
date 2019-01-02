package com.datasimulator.formatter;

import com.datasimulator.config.FieldConfig;
import org.json.JSONObject;

import java.util.List;

public class JsonDataFormatter implements DataFormatter<List<FieldConfig>> {

    public String formatData(List<FieldConfig> inputconfig) throws Exception{
        JSONObject jsonObject = new JSONObject();
        for(FieldConfig fieldConfig : inputconfig){
            jsonObject.put(fieldConfig.getFieldName(), fieldConfig.getGeneratedValue());
        }
        return jsonObject.toString();
    }
}
