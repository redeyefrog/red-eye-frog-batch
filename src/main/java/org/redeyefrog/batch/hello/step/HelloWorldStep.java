package org.redeyefrog.batch.hello.step;

import lombok.extern.slf4j.Slf4j;
import org.redeyefrog.batch.common.listener.FrogStepExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@Component
public class HelloWorldStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    FrogStepExecutionListener stepExecutionListener;

    int count = 1;

    @Bean
    Step helloStep(ItemReader<String> helloReader, ItemProcessor<String, String> helloProcessor, ItemWriter<String> helloWriter) {
        return stepBuilderFactory.get("helloStep")
                                 .listener(stepExecutionListener)
                                 .<String, String>chunk(5)
                                 .reader(helloReader)
                                 .processor(helloProcessor)
                                 .writer(helloWriter)
                                 .build();
    }

    @Bean
    ItemReader<String> helloReader() {
        return () -> {
            while (count < 8) {
                return MessageFormat.format("Hello World times({0})", count++);
            }
            return null;
        };
    }

    @Bean
    ItemProcessor<String, String> helloProcessor() {
        return (input) -> {
            if (input.contains("3")) {
                return MessageFormat.format("{0}!!", input);
            }
            return input;
        };
    }

    @Bean
    ItemWriter<String> helloWriter() {
        return (items) -> {
            items.stream().forEach(item -> log.info(item));
        };
    }

}
