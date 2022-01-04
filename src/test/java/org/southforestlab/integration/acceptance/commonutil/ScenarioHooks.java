package org.southforestlab.integration.acceptance.commonutil;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class ScenarioHooks {

    @Autowired
    private ScenarioContextApi apiContext;

    @LocalServerPort
    private int port;

    @Before("@api")
    public void setupForApi() {
    }

    @After("@api")
    public void tearDownForApi(Scenario scenario) throws IOException {
        apiContext.getReport().write(scenario);
    }
}
