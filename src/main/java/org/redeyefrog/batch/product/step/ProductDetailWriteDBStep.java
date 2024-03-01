package org.redeyefrog.batch.product.step;

import org.redeyefrog.batch.product.dto.ProductDetailDto;
import org.redeyefrog.batch.product.listener.ProductDetailWriteDBStepListener;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailWriteDBStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    ProductDetailWriteDBStepListener stepExecutionListener;

    @Bean
    Step write2ProductDetailStep(ItemReader<ProductDetailDto> write2ProductDetailReader, ItemProcessor<ProductDetailDto, ProductDetailEntity> write2ProductDetailProcessor, ItemWriter<ProductDetailEntity> write2ProductDetailWriter) {
        return stepBuilderFactory.get("write2ProductDetailStep")
                                 .listener(stepExecutionListener)
                                 .<ProductDetailDto, ProductDetailEntity>chunk(5)
                                 .reader(write2ProductDetailReader)
                                 .processor(write2ProductDetailProcessor)
                                 .writer(write2ProductDetailWriter)
                                 .build();
    }

}
