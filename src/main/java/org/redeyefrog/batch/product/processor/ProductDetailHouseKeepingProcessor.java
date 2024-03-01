package org.redeyefrog.batch.product.processor;

import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.redeyefrog.persistence.entity.ProductDetailHkpEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailHouseKeepingProcessor {

    @Bean
    ItemProcessor<ProductDetailEntity, ProductDetailHkpEntity> productDetailHkpProcessor() {
        return (entity) -> ProductDetailHkpEntity.builder()
                                                 .detailSeq(entity.getDetailSeq())
                                                 .masterSeq(entity.getMasterSeq())
                                                 .productName(entity.getProductName())
                                                 .quantity(entity.getQuantity())
                                                 .unitPrice(entity.getUnitPrice())
                                                 .amount(entity.getAmount())
                                                 .createTime(entity.getCreateTime())
                                                 .updateTime(entity.getUpdateTime())
                                                 .build();
    }

}
