package com.scaffold.integration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {IntegrationTestBase.DatabaseConnectionInjector.class})
public class IntegrationTestBase {

    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:5.7");

    static {
        MY_SQL_CONTAINER.start();
    }

    static class DatabaseConnectionInjector implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + MY_SQL_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + MY_SQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + MY_SQL_CONTAINER.getPassword(),
                "spring.datasource.driver-class-name=" + MY_SQL_CONTAINER.getDriverClassName(),
                "APP_VERSION=0"
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
