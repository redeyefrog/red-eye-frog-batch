package org.redeyefrog.batch.product.step;

import org.redeyefrog.batch.product.listener.ProductDetailHouseKeepingStepListener;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.redeyefrog.persistence.entity.ProductDetailHkpEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailHouseKeepingStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    ProductDetailHouseKeepingStepListener stepExecutionListener;

    @Bean
    Step productDetailHkpStep(ItemReader<ProductDetailEntity> productDetailHkpReader, ItemProcessor<ProductDetailEntity, ProductDetailHkpEntity> productDetailHkpProcessor, ItemWriter<ProductDetailHkpEntity> productDetailHkpWriter) {
        return stepBuilderFactory.get("productDetailHkpStep")
                                 .listener(stepExecutionListener)
                                 .<ProductDetailEntity, ProductDetailHkpEntity>chunk(5)
                                 .reader(productDetailHkpReader)
                                 .processor(productDetailHkpProcessor)
                                 .writer(productDetailHkpWriter)
                                 .build();
    }

}
