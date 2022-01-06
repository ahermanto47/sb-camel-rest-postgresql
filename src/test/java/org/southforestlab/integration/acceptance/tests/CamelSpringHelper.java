package org.southforestlab.integration.acceptance.tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

@CamelSpringBootTest
@SpringBootTest(classes = { Application.class })
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class CamelSpringHelper {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ProducerTemplate producerTemplate;

	public CamelContext getCamelContext() {
		return camelContext;
	}

	public ProducerTemplate getProducerTemplate() {
		return producerTemplate;
	}

	protected String generateJsonString(final String jsonFilePath) throws IOException {

		return Files.readString(Paths.get(jsonFilePath), StandardCharsets.UTF_8);
	}

	protected HashMap generateJsonObject(final String jsonFilePath) throws IOException {

		final ObjectMapper mapper = new ObjectMapper();
		final String json = Files.readString(Paths.get(jsonFilePath), StandardCharsets.UTF_8);
		return mapper.readValue(json, HashMap.class);
	}

	protected Exchange getDefaultExchange() {

		return new DefaultExchange(getCamelContext());

	}

	protected void resetMock(final List<MockEndpoint> mockEndpoints) {

		mockEndpoints.stream().forEach(m -> {
			m.reset();
		});

	}
}

