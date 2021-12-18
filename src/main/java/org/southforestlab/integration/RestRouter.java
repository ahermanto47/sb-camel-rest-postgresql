package org.southforestlab.integration;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

@Component
public class RestRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		rest("/members").description("Member REST service").consumes("application/json").produces("application/json")
				.put("/{id}").description("Update a member").type(Member.class).param().name("id").type(path)
				.description("The ID of the member to update").dataType("integer").endParam().param().name("body")
				.type(body).description("The member to update").endParam().responseMessage().code(204)
				.message("Member successfully updated").endResponseMessage().to("direct:update-member");

		from("direct:update-member").log("${body}").setHeader("id").jsonpath("$.Id", true).setHeader("accountId")
				.jsonpath("$.AccountId", true).setHeader("firstName").jsonpath("$.FirstName", true)
				.setHeader("lastName").jsonpath("$.LastName", true).setHeader("phone").jsonpath("$.Phone", true)
				.setHeader("email").jsonpath("$.Email", true)
				.setBody(simple(
						"insert into members values ('${header.id}', '${header.accountId}', '${header.firstName}', '${header.lastName}', '${header.phone}', '${header.email}')"))
				.to("jdbc:dataSource?useHeadersAsParameters=true").log("success").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
				.setBody(constant(""));
		;

	}

}
