package org.redeyefrog.batch.common.decider;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class FrogJobExecutionDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        BatchStatus batchStatus = jobExecution.getStatus();
        if (BatchStatus.STOPPED.equals(batchStatus)) {
            return FlowExecutionStatus.STOPPED;
        }
        return new FlowExecutionStatus(batchStatus.name());
    }

}
