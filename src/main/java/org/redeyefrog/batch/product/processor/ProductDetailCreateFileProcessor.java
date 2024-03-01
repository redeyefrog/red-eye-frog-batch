package org.redeyefrog.batch.product.processor;

import org.redeyefrog.batch.product.dto.ProductDetailDto;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.redeyefrog.persistence.repository.ProductMasterRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailCreateFileProcessor {

    @Autowired
    ProductMasterRepository productMasterRepository;

    @Bean
    ItemProcessor<ProductDetailEntity, ProductDetailDto> createFromProductDetailProcessor() {
        return (entity) -> ProductDetailDto.builder()
                                           .companyName(productMasterRepository.findById(entity.getMasterSeq()).get().getCompanyName())
                                           .productName(entity.getProductName())
                                           .quantity(entity.getQuantity())
                                           .unitPrice(entity.getUnitPrice())
                                           .amount(entity.getAmount())
                                           .createTime(entity.getCreateTime())
                                           .build();
    }

}
