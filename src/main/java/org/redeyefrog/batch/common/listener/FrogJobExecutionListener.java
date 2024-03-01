package org.redeyefrog.batch.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Slf4j
@JobScope
@Component
public class FrogJobExecutionListener /*implements JobExecutionListener*/ {

    @BeforeJob
//    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before {}.", jobExecution.getJobInstance().getJobName());
    }

    @AfterJob
//    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After {}.", jobExecution.getJobInstance().getJobName());
    }

}
