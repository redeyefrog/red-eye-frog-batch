package org.redeyefrog.batch.market.step;

import org.redeyefrog.batch.common.listener.FrogStepExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FruitSaleStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    FrogStepExecutionListener stepExecutionListener;

    @Bean
    Step appleStep(ItemReader<String> appleReader, ItemWriter<String> appleWriter) {
        return stepBuilderFactory.get("appleStep")
                                 .listener(stepExecutionListener)
                                 .<String, String>chunk(50)
                                 .reader(appleReader)
                                 .writer(appleWriter)
                                 .build();
    }

    @Bean
    Step bananaStep(ItemReader<String> bananaReader, ItemWriter<String> bananaWriter) {
        return stepBuilderFactory.get("bananaStep")
                                 .listener(stepExecutionListener)
                                 .<String, String>chunk(50)
                                 .reader(bananaReader)
                                 .writer(bananaWriter)
                                 .build();
    }

    @Bean
    Step orangeStep(ItemReader<String> orangeReader, ItemWriter<String> orangeWriter) {
        return stepBuilderFactory.get("orangeStep")
                                 .listener(stepExecutionListener)
                                 .<String, String>chunk(50)
                                 .reader(orangeReader)
                                 .writer(orangeWriter)
                                 .build();
    }

}
