package com.sevketbuyukdemir.config_server.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigDatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    public ConfigDatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadPropertiesFiles() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/configs/**/*.properties");

        for (Resource resource : resources) {
            Properties props = new Properties();
            try (InputStream in = resource.getInputStream()) {
                props.load(in);
            }

            String filename = resource.getFilename(); // e.g., application.properties
            String application = resource.getFile().getParentFile().getName();
            String profile = "default";

            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);
                jdbcTemplate.update(
                        "INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, propertyKEY, propertyVALUE) VALUES (?, ?, ?, ?, ?) " +
                                "ON DUPLICATE KEY UPDATE propertyVALUE=?",
                        application, profile, "main", key, value, value
                );
            }
        }
    }
}
