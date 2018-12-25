package com.datasimulator.factory;

import com.datasimulator.formatter.DataFormatter;
import com.datasimulator.metadata.OutputFormatType;

public class DataSimulatorFactory {

    private static final DataSimulatorFactory simulatorFactory =  new DataSimulatorFactory();

    private DataSimulatorFactory(){
    }

    public static DataSimulatorFactory getInstance(){
        return simulatorFactory;
    }

    public OutputFormatType getOutputFormatType(String formatterType){
        return OutputFormatType.valueOf(formatterType);
    }

    public DataFormatter getDataFormatter(String outputFormatType) throws Exception{

        for(OutputFormatType formatType : OutputFormatType.values()){
            if(formatType.getOutputFormat().equalsIgnoreCase(outputFormatType))
                return getInstanceUsingReflection(formatType.getFormatterClassName());
        }
        return null;
    }

    private <T> T getInstanceUsingReflection(String formatterClassName) throws Exception{
        return (T) Class.forName(formatterClassName).newInstance();
    }
}
