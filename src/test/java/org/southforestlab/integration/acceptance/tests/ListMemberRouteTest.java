package org.southforestlab.integration.acceptance.tests;

import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.southforestlab.integration.RestRouter;
import org.springframework.test.context.ContextConfiguration;

@UseAdviceWith
@ContextConfiguration(classes = { RestRouter.class })
public class ListMemberRouteTest extends CamelSpringHelper {


	@Test
	public void testListMemberRoute() throws Exception {

		AdviceWith.adviceWith(getCamelContext(), "list-member-route", a -> {

			a.weaveAddLast().to("mock:complete");

		});

		getCamelContext().start();

		// setup the mock endpoint
		try (MockEndpoint mockA = getCamelContext().getEndpoint("mock:complete", MockEndpoint.class)) {

			// we expect to process one message
			mockA.expectedMessageCount(1);

			// send the exchange
			getProducerTemplate().sendBody("direct:list-member", "");

			// verify it is as expected
			mockA.assertIsSatisfied();
			mockA.reset();

		}
	}

}
