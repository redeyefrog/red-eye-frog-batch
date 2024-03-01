package org.redeyefrog.batch.market.flow;

import org.redeyefrog.batch.common.decider.FrogJobExecutionDecider;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class FruitSaleFlow {

    @Bean
    Flow fruitFlow(Flow splitFruitFlow, FrogJobExecutionDecider decider) {
        return new FlowBuilder<Flow>("fruitFlow")
                .from(decider)
                .on(BatchStatus.STARTED.name())
                .to(splitFruitFlow)
                .build();
    }

    @Bean
    Flow splitFruitFlow(TaskExecutor executor, Flow appleFlow, Flow bananaFlow, Flow orangeFlow) {
        return new FlowBuilder<Flow>("splitFruitFlow")
                .split(executor)
                .add(appleFlow, bananaFlow, orangeFlow)
                .build();
    }

    @Bean
    TaskExecutor executor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(100);
        return executor;
    }

    @Bean
    Flow appleFlow(Step appleStep) {
        return new FlowBuilder<Flow>("appleFlow")
                .start(appleStep)
                .build();
    }

    @Bean
    Flow bananaFlow(Step bananaStep) {
        return new FlowBuilder<Flow>("bananaFlow")
                .start(bananaStep)
                .build();
    }

    @Bean
    Flow orangeFlow(Step orangeStep) {
        return new FlowBuilder<Flow>("orangeFlow")
                .start(orangeStep)
                .build();
    }

}
