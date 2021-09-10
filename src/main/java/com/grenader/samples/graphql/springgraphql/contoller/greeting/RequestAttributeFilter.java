package com.grenader.samples.graphql.springgraphql.contoller.greeting;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter that adds a Servlet request attribute.
 */
@Component
public class RequestAttributeFilter implements Filter {

	public static final String NAME_ATTRIBUTE = RequestAttributeFilter.class.getName() + ".name";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute(NAME_ATTRIBUTE, "007");
		chain.doFilter(request, response);
	}

}
