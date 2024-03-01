package org.redeyefrog.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InMemoryBatchConfig extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        // set nothing, will configure a map-based JobRepository, but that was deprecated at 4.3, will remove at 5.0
    }

}
