package ca.bc.gov.educ.api.report.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import javax.sql.DataSource;

@Configuration
@IntegrationComponentScan
@EnableIntegration
@ComponentScans({
        @ComponentScan("ca.bc.gov.educ.api.report"),
        @ComponentScan("ca.bc.gov.educ.isd"),
        @ComponentScan("ca.bc.gov.educ.grad")
})
public class ReportApiApplicationConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
