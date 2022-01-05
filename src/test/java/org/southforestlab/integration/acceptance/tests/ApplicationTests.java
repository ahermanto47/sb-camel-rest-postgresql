package org.southforestlab.integration.acceptance.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.southforestlab.integration.acceptance.tests")
public class ApplicationTests {

    public static void main(final String[] args) {
        SpringApplication.run(ApplicationTests.class);
    }
}
