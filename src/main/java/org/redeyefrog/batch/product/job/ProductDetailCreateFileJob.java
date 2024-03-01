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
public class ProductDetailCreateFileJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    FrogJobExecutionListener jobExecutionListener;

    @Bean
    Job createFromProductDetailJob(Step createFromProductDetailStep) {
        return jobBuilderFactory.get("createFromProductDetailJob")
                                .listener(jobExecutionListener)
                                .incrementer(new RunIdIncrementer())
                                .start(createFromProductDetailStep)
                                .build();
    }

}
