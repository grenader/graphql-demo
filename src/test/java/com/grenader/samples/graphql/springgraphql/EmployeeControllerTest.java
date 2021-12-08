package com.grenader.samples.graphql.springgraphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EmployeeControllerTest {

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


	@DisplayName("Given we query for all employees, and query is correct, then we get a successful response.")
	@Test
	void canQueryWithoutErrors() {
		String query = "{" +
				"  employees{ " +
				"    name " +
				"  }" +
				"}";

		this.graphQlTester.query(query).executeAndVerify();
	}

	@DisplayName("Given we query for all employees, and we request two fields to be included, " +
			"then all objects are returned with names and salaries.")
	@Test
	void canQuerySalary() {
		String query = "{" +
				"  employees{ incorrectFieldName" +
				"  }" +
				"}";

		AssertionError error = assertThrows(AssertionError.class, () -> {
			this.graphQlTester.query(query).executeAndVerify();
		});

		assertThat(error.getMessage()).startsWith("Response has 1 unexpected error").
				containsSubsequence("Field 'incorrectFieldName' in type 'Employee' is undefined");

		System.out.println("error message = " + error.getMessage());
	}

	@DisplayName("Given we query for all employees, and we request two fields to be included, " +
			"then all objects are returned with names and salaries.")
	@Test
	void canQueryWithSalary() {
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

	@DisplayName("Given we query for all employees, and we request only name to be included, " +
			"then all objects are returned with names only.")
	@Test
	void canQueryNamesOnly() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.path("employees[0].name").entity(String.class).isEqualTo("Andi")
				.path("employees[0].salary").valueDoesNotExist();

	}



}