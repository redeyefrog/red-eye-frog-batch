package org.redeyefrog.batch.market.job;

import org.redeyefrog.batch.common.listener.FrogJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class FruitSaleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    FrogJobExecutionListener jobExecutionListener;

    @Bean
    Job flowSaleJob(Flow fruitFlow, JobParametersIncrementer jobParametersIncrementer) {
        return jobBuilderFactory.get("fruitJob")
                                .listener(jobExecutionListener)
                                .incrementer(jobParametersIncrementer)
                                .start(fruitFlow)
                                .build()
                                .build();
    }

    @Bean
    JobParametersIncrementer jobParametersIncrementer() {
        return (jobParameters) -> new JobParametersBuilder(jobParameters)
                .addLong("timestamp", System.currentTimeMillis()) // void a job instance already exist and is complete for parameters
                .toJobParameters();
    }

}
