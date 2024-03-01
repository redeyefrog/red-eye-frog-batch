package org.redeyefrog.batch.product.writer;

import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class ProductDetailWriteDBWriter {

    @Bean
    ItemWriter<ProductDetailEntity> write2ProductDetailWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<ProductDetailEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
//        return (items) -> {
//            JpaItemWriter<ProductDetailEntity> jpaItemWriter = new JpaItemWriterBuilder<ProductDetailEntity>()
//                    .entityManagerFactory(entityManagerFactory)
//                    .build();
//            jpaItemWriter.write(items);
//        };
    }

}
