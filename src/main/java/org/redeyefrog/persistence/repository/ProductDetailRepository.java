package org.redeyefrog.persistence.repository;

import org.redeyefrog.batch.product.dto.ProductMasterDto;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {

    @Query(value = "SELECT new org.redeyefrog.batch.product.dto.ProductMasterDto(masterSeq, SUM(quantity), SUM(amount)) FROM ProductDetailEntity GROUP BY masterSeq ORDER BY masterSeq")
    List<ProductMasterDto> getAllProductByCompany();

}
