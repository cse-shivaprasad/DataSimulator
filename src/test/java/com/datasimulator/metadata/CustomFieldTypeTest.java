package com.datasimulator.metadata;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.generator.FakeValueGenerator;
import com.semihunaldi.excelorm.ExcelReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CustomFieldTypeTest {

    private List<FieldConfig> fieldConfigList;

    @Before
    public void preprocessFieldConfig()throws Exception{
        File file = new File("/Users/Shivaprasad/Documents/workspace_java/DataSimulator/src/main/resources/batchconfig/templates/BatchConsumerTransactions.xlsx");
        ExcelReader excelReader = new ExcelReader();
        fieldConfigList = excelReader.read(file, FieldConfig.class);
    }
    @Test
    public void testRandomStringValue() throws  Exception{

        FieldConfig fieldConfig = fieldConfigList.get(13);
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
        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.setCustomDataType("MASTER_CARD_NUMBER");
        for(int i=0; i<25; i++) {
            String cardNumber = FakeValueGenerator.getCreditCardNumber(fieldConfig);
            System.out.println("Random MasterCard Number :" + cardNumber);
            Assert.assertTrue(cardNumber.contains("-") && cardNumber.startsWith("6771"));
        }
    }

    @Test
    public void testVisaCardValue(){
        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.setCustomDataType("DISCOVER_CARD_NUMBER");
        for(int i=0; i<25; i++){
            String cardNumber = FakeValueGenerator.getCreditCardNumber(fieldConfig);
            System.out.println("Random Discover Card Number :"+cardNumber);
            int length = cardNumber.length();
            Assert.assertTrue(cardNumber.contains("-") && (length==19 || length ==24) );
        }


    }
    /*@Test
    public void testNameValue(){
        String value = CustomFieldType.valueOf(CustomFieldType.NAME.getFieldName())
                .getGeneratorFunction()
                .apply(FakeValueGenerator.getName(fieldConfigList.get(3)));
        System.out.println("Random MasterCard Number :"+value);
        Assert.assertTrue(value.contains("-"));
    }*/

}
