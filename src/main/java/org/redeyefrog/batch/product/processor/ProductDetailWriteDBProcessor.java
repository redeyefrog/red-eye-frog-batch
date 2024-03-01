package org.redeyefrog.batch.product.processor;

import org.redeyefrog.batch.product.dto.ProductDetailDto;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.redeyefrog.persistence.entity.ProductMasterEntity;
import org.redeyefrog.persistence.repository.ProductMasterRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDetailWriteDBProcessor {

    @Autowired
    ProductMasterRepository productMasterRepository;

    @Bean
    ItemProcessor<ProductDetailDto, ProductDetailEntity> write2ProductDetailProcessor() {
        return (dto) -> ProductDetailEntity.builder()
                                           .masterSeq(getMasterSeq(dto))
                                           .productName(dto.getProductName())
                                           .quantity(dto.getQuantity())
                                           .unitPrice(dto.getUnitPrice())
                                           .amount(dto.getAmount())
                                           .createTime(dto.getCreateTime())
                                           .build();
    }

    private Long getMasterSeq(ProductDetailDto dto) {
        String companyName = dto.getCompanyName();
        Optional<ProductMasterEntity> optional = productMasterRepository.findByCompanyName(companyName);
        ProductMasterEntity entity;
        if (optional.isEmpty()) {
            entity = new ProductMasterEntity();
            entity.setCompanyName(companyName);
            entity.setCreateTime(LocalDateTime.now());
            productMasterRepository.saveAndFlush(entity);
        } else {
            entity = optional.get();
        }
        return entity.getMasterSeq();
    }

}
