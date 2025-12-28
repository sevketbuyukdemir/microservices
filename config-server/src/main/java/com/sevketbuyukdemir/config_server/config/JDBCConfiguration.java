package com.sevketbuyukdemir.config_server.config;

import org.springframework.cloud.config.server.environment.JdbcEnvironmentProperties;
import org.springframework.cloud.config.server.environment.JdbcEnvironmentRepository;
import org.springframework.cloud.config.server.environment.JdbcEnvironmentRepository.PropertiesResultSetExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JDBCConfiguration {
    @Bean
    public JdbcEnvironmentRepository jdbcEnvironmentRepository(JdbcTemplate jdbcTemplate) {
        JdbcEnvironmentProperties properties = new JdbcEnvironmentProperties();
        properties.setSql("SELECT propertyKEY AS `KEY`, propertyVALUE AS `VALUE` FROM PROPERTIES WHERE APPLICATION=? AND PROFILE=? AND LABEL=?");
        properties.setDefaultLabel("main");
        PropertiesResultSetExtractor extractor = new PropertiesResultSetExtractor();
        return new JdbcEnvironmentRepository(jdbcTemplate, properties, extractor);
    }
}
