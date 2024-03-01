package org.redeyefrog.persistence.repository;

import org.redeyefrog.persistence.entity.ProductMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductMasterRepository extends JpaRepository<ProductMasterEntity, Long> {

    Optional<ProductMasterEntity> findByCompanyName(String companyName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ProductMasterEntity SET totalQuantity = ?1, totalAmount = ?2, updateTime = CURRENT_TIMESTAMP WHERE masterSeq = ?3")
    int updateTotalQuantityAndTotalAmount(BigDecimal totalQuantity, BigDecimal totalAmount, Long masterSeq);

}
