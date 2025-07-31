package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "server.port=0",
    "management.server.port=0"
})
class SampleApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring Boot context loads successfully
    }
}
