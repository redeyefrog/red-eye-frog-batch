package org.redeyefrog.batch.product.reader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.redeyefrog.persistence.entity.ProductDetailEntity;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailHouseKeepingReader {

    @Bean
    ItemReader<ProductDetailEntity> productDetailHkpReader(SqlSessionFactory sqlSessionFactory) {
        return new MyBatisPagingItemReaderBuilder<ProductDetailEntity>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("product_detail.findExpireDetail")
//                .parameterValues(Collections.emptyMap()) // query condition
                .maxItemCount(2147483647)
                .pageSize(10)
                .saveState(false)
                .build();
    }

}
