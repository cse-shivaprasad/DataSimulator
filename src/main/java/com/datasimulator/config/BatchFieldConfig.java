package com.datasimulator.config;

import com.semihunaldi.excelorm.BaseExcel;
import com.semihunaldi.excelorm.annotations.Excel;
import com.semihunaldi.excelorm.annotations.ExcelColumn;
import lombok.*;
import org.springframework.stereotype.Component;


@Component
@Excel(firstRow = 1, firstCol = 0, sheetName = "BatchFieldConfig")
@Data
public class BatchFieldConfig extends BaseExcel{

    @ExcelColumn(col = 0, columnName = "fieldName")
    private  String fieldName;

    @ExcelColumn(col = 1, columnName = "dataType")
    private String dataType;

    @ExcelColumn(col = 2, columnName = "customDataType")
    private String customDataType;

    @ExcelColumn(col = 3, columnName = "isUnique")
    private String isUnique;

    @ExcelColumn(col = 4, columnName = "variance")
    private Double variance;

    @ExcelColumn(col = 5, columnName = "fieldLength")
    private Integer fieldLength;

    @ExcelColumn(col = 6, columnName = "fieldIndex")
    private Integer fieldIndex;

    @ExcelColumn(col = 7, columnName = "fieldValueRange")
    private String fieldValueRange;

    @ExcelColumn(col = 8, columnName = "formula")
    private String formula;

    private String generatedValue;

}

