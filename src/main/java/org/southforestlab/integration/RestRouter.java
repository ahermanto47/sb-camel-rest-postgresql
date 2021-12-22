package org.southforestlab.integration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestRouter extends RouteBuilder {

	@Value("${camel.servlet.mapping.context-path}")
	private String contextPath;

	@Override
	public void configure() throws Exception {

		rest("/members").description("Member REST service").consumes("application/json").produces("application/json")
          .get().description("Find all members").responseMessage().code(200)
          .message("All members successfully returned").endResponseMessage()
          .to("direct:list-member");

		from("direct:list-member")
		  .setBody(constant("select * from member"))
		  .to("jdbc:dataSource?useHeadersAsParameters=true").log("success");
		
	}

}
