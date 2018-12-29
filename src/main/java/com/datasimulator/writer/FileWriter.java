package com.datasimulator.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter implements OutputWriter<String,String> {

    private BufferedWriter writer;

    @Override
    public void initializeResource(String outputFilePath) throws Exception{
        File outputFile= Paths.get(outputFilePath).toFile();
        if(!outputFile.exists()){
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
            outputFile.setExecutable(true);
            outputFile.setWritable(true);
        }
        writer = new BufferedWriter(new java.io.FileWriter(outputFilePath));
    }

    @Override
    public void writeOutputContent(String outputContent)throws Exception{
        writer.write(outputContent);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void closeResource() throws Exception{
        if(writer != null) {
            writer.close();
        }
    }
}
