package org.example.dev17final;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ContextConfiguration(classes = {TestcontainersConfiguration.class})
class Dev17FinalApplicationTests {

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> postgreSQLContainer = TestcontainersConfiguration.postgreSQLContainer;
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Test
    void contextLoads() {
    }

}
