package org.redeyefrog.batch.product.listener;

import lombok.extern.slf4j.Slf4j;
import org.redeyefrog.batch.product.dto.ProductMasterDto;
import org.redeyefrog.persistence.repository.ProductDetailRepository;
import org.redeyefrog.persistence.repository.ProductMasterRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@StepScope
@Component
public class ProductDetailWriteDBStepListener {

    @Autowired
    ProductMasterRepository productMasterRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before {}.", stepExecution.getStepName());
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After {}.", stepExecution.getStepName());
        try {
            List<ProductMasterDto> productMasterDtoList = productDetailRepository.getAllProductByCompany();
            for (ProductMasterDto productMasterDto : productMasterDtoList) {
                productMasterRepository.updateTotalQuantityAndTotalAmount(productMasterDto.getTotalQuantity(), productMasterDto.getTotalAmount(), productMasterDto.getMasterSeq());
            }
            return ExitStatus.COMPLETED;
        } catch (Exception e) {
            return ExitStatus.FAILED;
        }
    }

}
