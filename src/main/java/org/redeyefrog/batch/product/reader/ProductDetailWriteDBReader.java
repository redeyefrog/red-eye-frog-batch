package org.redeyefrog.batch.product.reader;

import org.redeyefrog.batch.product.dto.ProductDetailDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProductDetailWriteDBReader {

    @Bean
    ItemReader<ProductDetailDto> write2ProductDetailReader() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("company_name", "product_name", "quantity", "unit_price", "create_time");
        return new FlatFileItemReaderBuilder<ProductDetailDto>()
                .resource(new ClassPathResource("files/product_detail_20240226.txt"))
//                .resource(new FileSystemResource("path"))
//                .resource(new ByteArrayResource(new byte[0]))
                .linesToSkip(1)
                .lineTokenizer(lineTokenizer)
                .fieldSetMapper(fieldSetMapper())
                .encoding(StandardCharsets.UTF_8.displayName())
                .saveState(false) // A name is required when saveState is set to true. Reference FlatFileItemReader.
                .build();
    }

    private FieldSetMapper<ProductDetailDto> fieldSetMapper() {
        return (fieldSet) -> ProductDetailDto.builder()
                                             .companyName(fieldSet.readString("company_name"))
                                             .productName(fieldSet.readString("product_name"))
                                             .quantity(fieldSet.readBigDecimal("quantity"))
                                             .unitPrice(fieldSet.readBigDecimal("unit_price"))
                                             .amount(fieldSet.readBigDecimal("quantity").multiply(fieldSet.readBigDecimal("unit_price")))
                                             .createTime(LocalDate.parse(fieldSet.readString("create_time"), DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay())
                                             .build();
    }

}
