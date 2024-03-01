package org.redeyefrog.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "PRODUCT_DETAIL_HKP", schema = "REFROG")
public class ProductDetailHkpEntity {

    @Id
    @Column(name = "DETAIL_SEQ")
    private Long detailSeq;

    @Column(name = "MASTER_SEQ")
    private Long masterSeq;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

}
