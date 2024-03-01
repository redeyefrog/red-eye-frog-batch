package org.redeyefrog.batch.product.writer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.redeyefrog.persistence.entity.ProductDetailHkpEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailHouseKeepingWriter {

    @Bean
    ItemWriter<ProductDetailHkpEntity> productDetailHkpWriter(SqlSessionFactory sqlSessionFactory) {
        return new MyBatisBatchItemWriterBuilder<ProductDetailHkpEntity>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("product_detail_hkp.insertExpireDetail")
                .build();
    }

}
