package org.redeyefrog.batch.product.writer;

import org.redeyefrog.batch.product.dto.ProductDetailDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDetailCreateFileWriter {

    @Bean
    ItemWriter<ProductDetailDto> createFromProductDetailWriter() {
        return new FlatFileItemWriterBuilder<ProductDetailDto>()
                .name("create-product-detail-file")
                .resource(new PathResource("product_detail_20240227.txt"))
                .encoding(StandardCharsets.UTF_8.displayName())
                .lineAggregator(lineAggregator()) // A LineAggregator or a DelimitedBuilder or a FormattedBuilder is required
//                .delimited()
//                .delimiter("|") // default ,
//                .names("companyName", "productName", "quantity", "unitPrice")
//                .fieldExtractor(fieldExtractor())
//                .formatted()
//                .format("%-5s%-10s%08d%06d%8s")
//                .fieldExtractor(fieldExtractor())
                .build();
    }

    private LineAggregator<ProductDetailDto> lineAggregator() {
        DelimitedLineAggregator<ProductDetailDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor());
//        FormatterLineAggregator<ProductDetailDto> lineAggregator = new FormatterLineAggregator<>();
//        lineAggregator.setFormat("%-5s%-10s%08d%06d%8s");
//        lineAggregator.setFieldExtractor(fieldExtractor());
        return lineAggregator;
    }

    private FieldExtractor<ProductDetailDto> fieldExtractor() {
        return (dto) -> {
            List<Object> lists = new ArrayList<>();
            lists.add(dto.getCompanyName());
            lists.add(dto.getProductName());
            lists.add(dto.getQuantity().intValue());
            lists.add(dto.getUnitPrice().intValue());
            lists.add(dto.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            return lists.toArray();
        };
    }

}
