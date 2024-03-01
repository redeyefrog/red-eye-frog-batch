package org.redeyefrog.batch.product.reader;

import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;

@Component
public class ProductDetailCreateFileReader {

    @Bean(destroyMethod = "") // Custom destroy method 'close' on bean with name '' threw an exception: org.springframework.batch.item.ItemStreamException: Error while closing item reader
    ItemReader<ProductDetailEntity> createFromProductDetailReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<ProductDetailEntity>()
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT detail FROM ProductDetailEntity detail")
                .parameterValues(Collections.EMPTY_MAP)
//                .maxItemCount(2147483647) // use default 2147483647 or select count(*)
                .pageSize(5)
                .saveState(false) // if used in a multi-thread client
                .build();
    }

}
