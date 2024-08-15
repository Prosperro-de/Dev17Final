package org.example.dev17final;

import org.springframework.boot.SpringApplication;

public class TestContainerDemoApplication {
    public static void main(String[] args) {
        SpringApplication
                .from(Dev17FinalApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
