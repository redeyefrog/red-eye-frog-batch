package org.redeyefrog.batch.product.job;

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
public class ProductDetailWriteDBJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    FrogJobExecutionListener jobExecutionListener;

    @Bean
    Job write2ProductDetailJob(Step write2ProductDetailStep) {
        return jobBuilderFactory.get("write2ProductDetailJob")
                                .listener(jobExecutionListener)
                                .incrementer(new RunIdIncrementer())
                                .start(write2ProductDetailStep)
                                .build();
    }

}
