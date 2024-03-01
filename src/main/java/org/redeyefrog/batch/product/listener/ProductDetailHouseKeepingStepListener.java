package org.redeyefrog.batch.product.listener;

import lombok.extern.slf4j.Slf4j;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.redeyefrog.persistence.entity.ProductDetailHkpEntity;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@StepScope
@Component
public class ProductDetailHouseKeepingStepListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before {}.", stepExecution.getStepName());
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After {}.", stepExecution.getStepName());
        return ExitStatus.COMPLETED;
    }

    @OnReadError
    public void onReadError(Exception e) {
        log.error("On read error: {}", e.getMessage(), e);
    }

    @OnProcessError
    public void onProcessError(ProductDetailEntity entity, Exception e) {
        log.error("On process error: {}", e.getMessage(), e);
    }

    @AfterWrite
    public void afterWrite(List<ProductDetailHkpEntity> lists) {
        log.info("Write to PRODUCT_DETAIL_HKP with DETAIL_SEQ={}", lists.stream().map(ProductDetailHkpEntity::getDetailSeq).collect(Collectors.toList()));
    }

    @OnWriteError
    public void onWriteError(Exception e, List<ProductDetailHkpEntity> lists) {
        log.error("On write error: {}", e.getMessage(), e);
    }

}
