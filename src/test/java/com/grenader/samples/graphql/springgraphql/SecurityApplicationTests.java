package com.grenader.samples.graphql.springgraphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Disabled
class SecurityApplicationTests {

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
	void printError() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.errors()
				.satisfy(System.out::println);
	}

	@Test
	void anonymousThenUnauthorized() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.errors()
				.satisfy(errors -> {
					assertThat(errors).hasSize(1);
					assertThat(errors.get(0).getErrorType()).isEqualTo(ErrorType.UNAUTHORIZED);
				});
	}

	@Test
	void userRoleThenForbidden() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.headers(headers -> headers.setBasicAuth("rob", "rob"))
				.execute()
				.errors()
				.satisfy(errors -> {
					assertThat(errors).hasSize(1);
					assertThat(errors.get(0).getErrorType()).isEqualTo(ErrorType.FORBIDDEN);
				});
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
	void canNotQuerySalary() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		this.graphQlTester.query(query)
				.execute()
				.errors()
				.satisfy(errors -> {
					assertThat(errors).hasSize(1);
					assertThat(errors.get(0).getErrorType()).isEqualTo(ErrorType.UNAUTHORIZED);
				});
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
				.headers(headers -> headers.setBasicAuth("admin", "admin"))
				.execute()
				.path("employees[0].name").entity(String.class).isEqualTo("Andi")
				.path("employees[0].salary").entity(int.class).isEqualTo(42);
	}

	@Test
	void invalidCredentials() {
		String query = "{" +
				"  employees{ " +
				"    name" +
				"    salary" +
				"  }" +
				"}";

		assertThatThrownBy(() ->
				this.graphQlTester.query(query)
						.headers(headers -> headers.setBasicAuth("admin", "INVALID"))
						.executeAndVerify())
				.hasMessage("Status expected:<200 OK> but was:<401 UNAUTHORIZED>");
	}

}