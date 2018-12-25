package com.datasimulator.driver;

import com.datasimulator.orchestrator.BatchSimulationOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.datasimulator.configreader","com.datasimulator.orchestrator","com.datasimulator.config"})
@SpringBootApplication
public class SpringBootDataSimulationDriver implements CommandLineRunner{

    @Autowired
    private BatchSimulationOrchestrator batchSimulationOrchestrator;

    public static void main(String[] args) {

        System.out.println("Initializing DataSimulatorDriver....");
       /* SpringApplication app = new SpringApplication(BatchDataSimulationDriver.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);*/

        SpringApplication.run(SpringBootDataSimulationDriver.class, args);
    }

    @Override
    public void run(String... args) throws Exception{

        if (args.length != 1) {
            System.out.println("Argument Count Mismatch. Exiting the process");
            throw new IllegalArgumentException();
        }
        System.out.println(">>>>>>>> Job Name>>>>>>>>>"+batchSimulationOrchestrator.getTemplateConfig());

    }
}
