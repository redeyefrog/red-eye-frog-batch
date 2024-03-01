package org.redeyefrog.batch.market.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FruitSaleWriter {

    @Bean
    ItemWriter<String> appleWriter() {
        return items -> {
            items.stream().forEach(item -> log.info(item));
        };
    }

    @Bean
    ItemWriter<String> bananaWriter() {
        return items -> {
            items.stream().forEach(item -> log.info(item));
        };
    }

    @Bean
    ItemWriter<String> orangeWriter() {
        return items -> {
            items.stream().forEach(item -> log.info(item));
        };
    }

}
