package com.datasimulator.metadata;

import com.datasimulator.config.BatchFieldConfig;
import com.datastax.driver.core.querybuilder.Batch;
import com.semihunaldi.excelorm.ExcelReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CustomFieldTypeTest {

    private List<BatchFieldConfig> fieldConfigList;

    @Before
    public void preprocessFieldConfig()throws Exception{
        File file = new File("/Users/Shivaprasad/Documents/workspace_java/DataSimulator/src/main/resources/batchconfig/templates/BatchConsumerTransactions.xlsx");
        ExcelReader excelReader = new ExcelReader();
        fieldConfigList = excelReader.read(file, BatchFieldConfig.class);
    }

    @Test
    public void testRandomStringValue() throws  Exception{

        BatchFieldConfig fieldConfig = fieldConfigList.get(13);
        JSONObject possibleValues = new JSONObject(fieldConfig.getFieldValueRange());
        JSONArray arrayValues = possibleValues.getJSONArray("POSSIBLE_VALUES");

        String value = CustomFieldType.valueOf(CustomFieldType.RANDOM_STRING.getFieldName())
                .getGeneratorFunction()
                .apply(fieldConfig);
        System.out.println("Random String Value "+value);
        Assert.assertNotNull(value);
        //Assert.assertThat(arrayValues,Assert);

    }

    @Test
    public void testRandomLongValue(){
        String value = CustomFieldType.valueOf(CustomFieldType.RANDOM_LONG.getFieldName())
                        .getGeneratorFunction()
                        .apply(fieldConfigList.get(0));
        //assertNo(value)
        Assert.assertNotNull(value);
    }

    @Test
    public void testRandomEmailValue(){
        String value = CustomFieldType.valueOf(CustomFieldType.EMAIL.getFieldName())
                .getGeneratorFunction()
                .apply(fieldConfigList.get(5));
        System.out.println("Random Email :"+value);
        Assert.assertTrue(value.contains("@"));
    }

    @Test
    public void testMasterCardValue(){
        String value = CustomFieldType.valueOf(CustomFieldType.MASTER_CARD_NUMBER.getFieldName())
                .getGeneratorFunction()
                .apply(fieldConfigList.get(3));
        System.out.println("Random MasterCard Number :"+value);
        Assert.assertTrue(value.contains("-"));
    }


}
