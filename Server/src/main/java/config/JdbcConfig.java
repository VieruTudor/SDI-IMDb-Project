package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    private final String dataSourceUrl = "jdbc:postgresql://rogue.db.elephantsql.com:5432/mwauuqab";

    @Bean
    JdbcOperations jdbcOperations(DataSource dataSource){
        JdbcTemplate jdbcTemplate= new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dataSourceUrl);
        basicDataSource.setUsername("mwauuqab");
        basicDataSource.setPassword("ocvl9Z95H3Qp4i06S0686yqqthL83qlS");
        basicDataSource.setInitialSize(2);
        return basicDataSource;
    }
}
