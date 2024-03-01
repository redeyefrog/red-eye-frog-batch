package org.redeyefrog.batch.hello.job;

import org.redeyefrog.batch.common.listener.FrogJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class HelloWorldJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    FrogJobExecutionListener jobExecutionListener;

    @Bean
    Job helloJob(Step helloStep) {
        return jobBuilderFactory.get("helloJob")
                                .listener(jobExecutionListener)
                                .incrementer(new RunIdIncrementer()) // run.id
                                .start(helloStep)
                                .build();
    }

}
