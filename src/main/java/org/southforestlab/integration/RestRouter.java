package org.southforestlab.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class RestRouter extends RouteBuilder {

	@Value("${camel.servlet.mapping.context-path}")
	private String contextPath;

    @Autowired
    private Environment env;
    
	@Override
	public void configure() throws Exception {

        restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.json)
        .dataFormatProperty("prettyPrint", "true")
        .enableCORS(true)
        .port(env.getProperty("server.port", "8080"))
        .contextPath(contextPath.substring(0, contextPath.length() - 2))
        // turn on openapi api-doc
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "Member API")
        .apiProperty("api.version", "1.0.0")
        .apiProperty("base.path","api");
        
		rest("/members").description("Member REST service").produces("application/json")
          .get().description("Find all members").responseMessage().code(200)
          .message("All members successfully returned").endResponseMessage()
          .to("direct:list-member");

		from("direct:list-member")
		  .id("list-member-route")
		  .setBody(constant("select * from member"))
		  .to("jdbc:dataSource?useHeadersAsParameters=true").log("success");
		
	}

}
