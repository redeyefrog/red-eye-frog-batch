package org.redeyefrog.batch.product.step;

import org.redeyefrog.batch.common.listener.FrogStepExecutionListener;
import org.redeyefrog.batch.product.dto.ProductDetailDto;
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
public class ProductDetailCreateFileStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    FrogStepExecutionListener stepExecutionListener;

    @Bean
    Step createFromProductDetailStep(ItemReader<ProductDetailEntity> createFromProductDetailReader, ItemProcessor<ProductDetailEntity, ProductDetailDto> createFromProductDetailProcessor, ItemWriter<ProductDetailDto> createFromProductDetailWriter) {
        return stepBuilderFactory.get("createFromProductDetailStep")
                                 .listener(stepExecutionListener)
                                 .<ProductDetailEntity, ProductDetailDto>chunk(5)
                                 .reader(createFromProductDetailReader)
                                 .processor(createFromProductDetailProcessor)
                                 .writer(createFromProductDetailWriter)
                                 .build();
    }

}
