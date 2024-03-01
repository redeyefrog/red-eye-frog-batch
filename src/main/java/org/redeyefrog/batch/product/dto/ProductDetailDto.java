package org.redeyefrog.batch.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductDetailDto {

    private String companyName;

    private String productName;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal amount;

    private LocalDateTime createTime;

}
