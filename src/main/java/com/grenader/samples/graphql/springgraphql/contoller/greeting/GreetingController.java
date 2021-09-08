package com.grenader.samples.graphql.springgraphql.contoller.greeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Controller
public class GreetingController {

    @Value("${app.name:Super GraphQL Demo}")
    private String applicationName;

    @QueryMapping
    public String hello() {
        return "Hello. My name is '" + applicationName+"'";
    }

    @QueryMapping
    public String greeting() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return "Greetings from " + attributes.getAttribute(RequestAttributeFilter.NAME_ATTRIBUTE, SCOPE_REQUEST);
    }

}