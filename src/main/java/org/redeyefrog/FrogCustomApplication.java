package org.redeyefrog;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication
public class FrogCustomApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JobLauncher jobLauncher;

    public static void main(String[] args) {
        SpringApplication.run(FrogCustomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (String arg : args) {
            Job job = (Job) applicationContext.getBean(arg);
            jobLauncher.run(job, new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters());
        }
    }

}
