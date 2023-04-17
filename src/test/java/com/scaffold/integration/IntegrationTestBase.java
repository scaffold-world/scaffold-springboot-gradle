package com.scaffold.integration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {IntegrationTestBase.DatabaseConnectionInjector.class})
public class IntegrationTestBase {

    private static final PostgreSQLContainer<?> DB_CONTAINER = new PostgreSQLContainer<>("postgres:15.2");

    static {
        DB_CONTAINER.start();
    }

    static class DatabaseConnectionInjector implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + DB_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + DB_CONTAINER.getUsername(),
                "spring.datasource.password=" + DB_CONTAINER.getPassword(),
                "spring.datasource.driver-class-name=" + DB_CONTAINER.getDriverClassName(),
                "APP_VERSION=0"
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
