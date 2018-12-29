package com.datasimulator.poc.excelorm;

import com.datasimulator.config.FieldConfig;
import com.datasimulator.metadata.CustomFieldType;
import com.semihunaldi.excelorm.ExcelReader;

import java.io.File;
import java.util.List;

public class ExcelORMTest {

    public static void main(String[] args) throws Exception{

        File file = new File("/Users/Shivaprasad/Documents/workspace_java/DataSimulator/src/main/resources/batchconfig/templates/BatchConsumerTransactions.xlsx");
        //reading
        ExcelReader excelReader = new ExcelReader();
        List<FieldConfig> fieldConfigList = excelReader.read(file, FieldConfig.class);
        String value = CustomFieldType.valueOf(CustomFieldType.RANDOM_LONG.getFieldName()).getGeneratorFunction().apply(fieldConfigList.get(0));
        System.out.println("V ===>"+value);

    }
}
