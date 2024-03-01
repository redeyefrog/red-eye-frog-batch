package org.redeyefrog.batch.market.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class FruitSaleReader {

    int appleSellCount = 1;

    int bananaSellCount = 1;

    int orangeSellCount = 1;

    @Bean
    ItemReader<String> appleReader() {
        return () -> {
            while (appleSellCount < 108) {
                return MessageFormat.format("Sell {0} apple", appleSellCount++);
            }
            return null;
        };
    }

    @Bean
    ItemReader<String> bananaReader() {
        return () -> {
            while (bananaSellCount < 307) {
                return MessageFormat.format("Sell {0} banana", bananaSellCount++);
            }
            return null;
        };
    }

    @Bean
    ItemReader<String> orangeReader() {
        return () -> {
            while (orangeSellCount < 202) {
                return MessageFormat.format("Sell {0} orange", orangeSellCount++);
            }
            return null;
        };
    }

}
