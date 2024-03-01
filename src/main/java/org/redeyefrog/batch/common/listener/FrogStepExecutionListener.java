package org.redeyefrog.batch.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@StepScope
@Component
public class FrogStepExecutionListener /*implements StepExecutionListener*/ {

    @BeforeStep
//    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before {}.", stepExecution.getStepName());
    }

    @AfterStep
//    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After {}.", stepExecution.getStepName());
        return ExitStatus.COMPLETED;
    }

    @BeforeRead
    public void beforeRead() {
        log.info("Before read");
    }

    @AfterRead
    public void afterRead(Object item) {
        log.info("After read");
    }

    @OnReadError
    public void onReadError(Exception e) {
        log.error("On read error: {}", e.getMessage(), e);
    }

    @BeforeProcess
    public void beforeProcess(Object input) {
        log.info("Before process");
    }

    @AfterProcess
    public void afterProcess(Object input, Object output) {
        log.info("After process");
    }

    @OnProcessError
    public void onProcessError(Object input, Exception e) {
        log.error("On process error: {}", e.getMessage(), e);
    }

    @BeforeWrite
    public void beforeWrite(List<Object> items) {
        log.info("Before write");
    }

    @AfterWrite
    public void afterWrite(List<Object> items) {
        log.info("After write");
    }

    @OnWriteError
    public void onWriteError(Exception e, List<Object> items) {
        log.error("On write error: {}", e.getMessage(), e);
    }

}
