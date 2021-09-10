package com.grenader.samples.graphql.springgraphql.contoller.greeting;

import org.springframework.graphql.execution.ThreadLocalAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

/**
 * {@link ThreadLocalAccessor} to expose a thread-bound RequestAttributes object to data
 * fetchers in Spring GraphQL.
 */
@Component
public class RequestAttributesAccessor implements ThreadLocalAccessor {

	private static final String KEY = RequestAttributesAccessor.class.getName();

	@Override
	public void extractValues(Map<String, Object> container) {
		container.put(KEY, RequestContextHolder.getRequestAttributes());
	}

	@Override
	public void restoreValues(Map<String, Object> values) {
		if (values.containsKey(KEY)) {
			RequestContextHolder.setRequestAttributes((RequestAttributes) values.get(KEY));
		}
	}

	@Override
	public void resetValues(Map<String, Object> values) {
		RequestContextHolder.resetRequestAttributes();
	}

}
