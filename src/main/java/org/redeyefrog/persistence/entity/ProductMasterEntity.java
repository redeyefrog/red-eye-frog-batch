package org.redeyefrog.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "PRODUCT_MASTER", schema = "REFROG")
public class ProductMasterEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_MASTER_SEQ")
    @SequenceGenerator(name = "PRODUCT_MASTER_SEQ", sequenceName = "PRODUCT_MASTER_SEQUENCE", schema = "REFROG", allocationSize = 1)
    @Id
    @Column(name = "MASTER_SEQ")
    private Long masterSeq;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "TOTAL_QUANTITY")
    private BigDecimal totalQuantity;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

}
