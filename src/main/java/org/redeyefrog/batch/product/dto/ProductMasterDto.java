package org.redeyefrog.batch.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductMasterDto {

    private Long masterSeq;

    private BigDecimal totalQuantity;

    private BigDecimal totalAmount;

}
