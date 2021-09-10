# Spring GraphQL Demo

It's a StringBoot application that exposes [GraphQL API](https://docs.spring.io/spring-graphql/docs/1.0.0-M2/reference/html/). It uses [Lombok](https://projectlombok.org/setup/gradle)

It needs **Java 11**! Make sure that you are running it on before starting the application or opening the project.

## Documentation
Main Documentation is here: https://docs.spring.io/spring-graphql/docs/1.0.0-M2/reference/html/

### Check your JDK:
```
java -version
```
Output. Something like this:
```
java version "11.0.9" 2020-10-20 LTS
...
```

# Start the application

## Start from command line:
```
./gradlew bootRun
```

## Start in a debug:
Open the project in your IDEA (I suggest IntelliJ IDEA). It is useful to have *JS GraphQL* plugin installed.
and run **SpringGraphqlSampleApplication** class in a debug.

## Access the application
Go to http://localhost:8080/actuator/
You can check different actuator endpoints enabled there. If you see a page with JSON, the application works for you.

## Where is the main configuration
You will find all queries in **schema.graphqls** file. We can use several files as well.

The schema can be seen via browser as well: http://localhost:8080/graphql/schema

## Make GraphQL calls:
The application comes with in-built [GraphiQL interface](https://github.com/spring-projects/spring-graphql/blob/main/spring-graphql-docs/src/docs/asciidoc/boot-starter.adoc#graphiql). 
You can access it via http://localhost:8080/graphiql URL
This is how it looks:
![GraphiQL UII in the browser](/doc/images/graphiql-ui.png)


Try the following queries. Just enter provided queries to the windows on the left and press 'Run' button on the top.
The interface has a useful auto-complete feature.

### hello
Simple String query with a response value from a property.
```
query {
  hello
}
```
Look at GreetingController.hello() method to see what it is doing.

### greeting
Simple String query with a response set by a filter. This demonstrates a mechanism of RequestAttributeFilter. It is a filter that gets called before the request is processed by GraphQL.  
```
query {
  greeting
}
```
Look at GreetingController.greeting() method to see what it is doing. You can also look at RequestAttributeFilter class.

### employees with their salaries
```
query {
  employees {
    id, 
    name, 
    salary
  }
}
```
This query will return a list of employees with their salaries.
The employees are coming from a hardcoded repository. Look at EmployeeService.class
and their salaries are coming from SalaryController.salary() method that is annotated with @SchemaMapping (look here for more details: https://docs.spring.io/spring-graphql/docs/1.0.0-SNAPSHOT/reference/html/#controllers-mapping)

### just employee names
```
query {
  employees {
    name
  }
}
```
This is in-built feature of GraphQL, there is no need to implement anything.

### load one employee by his/her id
```
query {
  employee(id: 3)
  {
    id, name
  }
}
```
Look at the EmployeeAndSalaryController.employee() method.

### filter by name and salary as parameters
```
query {
  filterEmployees(name: "Ivan", salary: 45) {
    id, name
  } 
}
```
Look at the EmployeeAndSalaryController.filterEmployees() - it is not link to any DB yet.

### filter using and object (LinkFilter.class) with optional parameters
```
query {
  filter2Employees(filter: {name: "Ivan"}) {
    id, name
  } 
}
```
Look at the EmployeeAndSalaryController.filter2Employees(), check how we use LinkFilter class.


## Integration with Spring-Data-JPA
This iis probably not the best implementation, it's straing-forward, but could be better.
It uses in-memory h2 database. 

It is a standard way of using spring-data-jpa.

Please, look at 
- model classes in **model** package
- repositories in **repository** package
- data service interfaces and their implementations in **service** package
- and controllers in **controller.book** package.

We also have two graphqls (schema) files. One is for types/classes and GET operations and the second is for mutations (POST, PUT).

There are three linked entities, they are Publisher, Author and Book.

Try the following queries ont by one form top to the bottom to the see the functionality in action:
```

mutation addPublisher {
  addPublisher(name: "My lovely publishes") {
    id
  }
  
}

mutation addAuthor {
  addAuthor(publisherId:1, name: "John Smith") {
    id
  }
  
}


mutation addBook {
  addBook(title: "The second book", ISBN: "23456", publisherId:1, authorId: 52) {
    id
  }
  
}

query getABook {
  bookById(id: 102) {
    id, title, author{name}, publisher {
      authors {
        id
      }
    }
  }
}

query getAnAuthor {
  authorById (id: 52) {
    id, name
  }
}
```

You will be able to create a publisher with ID=1, author with ID=52 and the boor will get ID=102.
Check that out.

## Unit Tests
We are using [WebTestClient.class](https://www.baeldung.com/spring-5-webclient) to test the application with [WebGraphQlTester.class](https://docs.spring.io/spring-graphql/docs/1.0.0-M2/reference/html/#boot-graphql-testing) to get better testing capabilities.

Look at **EmployeeApplicationTests.class** for more details.

Testing documentation is here: https://docs.spring.io/spring-graphql/docs/1.0.0-M2/reference/html/#boot-graphql-testing


## GraphQL plugin in IDEA.
GraphQL plugin will notify you about errors in schema file(s).
![GraphQL Plugin](/doc/images/graphql-plugin.png)

It also allows to call your GraphQL right from IDEA.
![GraphQL Plugin to query](/doc/images/query-via-plugin.png)

and get results
![GraphQL Plugin Query Results](/doc/images/query-results.png)









