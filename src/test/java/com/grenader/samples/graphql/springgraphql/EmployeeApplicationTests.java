package com.grenader.samples.graphql.springgraphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	WebApplicationContext context;

	private WebGraphQlTester graphQlTester;

	@BeforeEach
	public void setUp()
	{
		WebTestClient client =
				MockMvcWebTestClient.bindToApplicationContext(context)
						.configureClient()
						.baseUrl("/graphql")
						.build();

		graphQlTester = WebGraphQlTester.builder(client).build();
	}

	@Test
	void printGreeting() {
		String query = "{" +
				"  greeting"+
				"}";

		this.graphQlTester.query(query)
				.execute()
				.errors()
				.satisfy(System.out::println);
	}


	@Test
	void canQueryName() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.path("employees[0].name").entity(String.class).isEqualTo("Andi");
	}

	@Test
	void canQuerySalaryAsAdmin() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.path("employees[0].name").entity(String.class).isEqualTo("Andi")
				.path("employees[0].salary").entity(Integer.class).isEqualTo(25);
	}


}