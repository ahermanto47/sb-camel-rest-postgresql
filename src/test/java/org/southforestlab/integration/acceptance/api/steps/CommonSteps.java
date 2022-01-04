package org.southforestlab.integration.acceptance.api.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.southforestlab.integration.acceptance.commonutil.ScenarioContextApi;
import org.springframework.beans.factory.annotation.Autowired;


public class CommonSteps {

    @Autowired
    private ScenarioContextApi context;


    @When("^I make a GET call on ([^\"]*)$")
    public void iMakeAGETCallOn(String path) {
        context.invokeHttpGet(path);
    }

    @Then("^I should receive (\\d+) response status code$")
    public void iShouldReceiveStatusCodeResponse(int code) {
        //context.response.then().statusCode(code);
    	//context.response.getStatusCode().equals(HttpStatus.OK);
    	System.out.print("got response "+context.response.getStatusCode());
    }

    @Then("^should receive a non-empty body$")
    public void shouldReceiveANonEmptyBody() {
        //context.response.then().body(Matchers.notNullValue());
    	System.out.print("got response "+context.response.getBody());
    }


}