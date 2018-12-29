package com.datasimulator.poc.metamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import com.sun.source.tree.AssertTree;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.excel.ExcelDataContext;
import org.apache.metamodel.query.CompiledQuery;
import org.apache.metamodel.query.Query;
import org.apache.metamodel.query.SelectItem;
import org.apache.metamodel.query.builder.SatisfiedSelectBuilder;
import org.apache.metamodel.schema.Column;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.apache.metamodel.util.FileResource;
import org.apache.metamodel.util.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class ExcelReadingTest {

    private DataContext dataContext;

    public static void main(String[] args) throws Exception{

        System.out.println("Hello World");
        new ExcelReadingTest().testReadExcelSheets();
    }

    @Test
    public void testReadExcelSheets() throws Exception{
        String filePath = "/Users/Shivaprasad/Documents/workspace_java/DataSimulator/src/main/resources/batchconfig/templates/BatchConsumerTransactions.xlsx";

        ExcelDataContext excel = new ExcelDataContext(new File(filePath));
        assertNotNull("DataContext ===>"+excel);

        //ExcelDataContext excel = (ExcelDataContext) dataContext;
        Schema schema = excel.getSchemas().get(1);
        //schema.getTableNames().stream().map(tableName -> tableName+",").forEach(System.out::print);
        Table table = schema.getTableByName("FieldConfig");
        assertNotNull(table);

        table.getColumnNames().stream().map(name->name+",").forEach(System.out::print);

        table.getNumberColumns().stream().map(numberColumn->numberColumn+",").forEach(System.out::println);
        // System.out.println("Table :"+);

        Query query = excel.query().from(table).select(table.getColumns()).toQuery();
        DataSet dataSet = excel.executeQuery(query);
        while(dataSet.next()){
            Row row = dataSet.getRow();
            row.getSelectItems().stream().forEach(column -> System.out.print(row.getValue(column)+"|"));
            System.out.println();
        }
        //assertEquals(FileResource.class, resource.getClass());

        //assertEquals("BatchConsumerTransactions.xlsx", resource.getName());


    }
}
