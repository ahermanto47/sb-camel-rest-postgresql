package org.southforestlab.integration.acceptance.commonutil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@Scope(scopeName = "cucumber-glue")
public class ScenarioContextApi {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private ScenarioReport report;
    public ResponseEntity<String> response;
    public Map<String, Object> postBody = new HashMap<>();
    public Map<String, Object> queryParams = new HashMap<>();

    public ScenarioContextApi() {
        reset();
    }

    private void reset() {
        report = new ScenarioReport();
        response = null;
        postBody.clear();
        queryParams.clear();
    }

    public void invokeHttpGet(String path, Object... pathParams) {
        // Call the REST API
        response = restTemplate.getForEntity(path, String.class);
    }

    public ScenarioReport getReport() {
        return report;
    }

}
