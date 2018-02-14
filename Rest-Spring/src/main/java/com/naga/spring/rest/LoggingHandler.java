package com.naga.spring.rest;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class LoggingHandler extends CommonsRequestLoggingFilter {

	public LoggingHandler() {
		super.setIncludeQueryString(true);
		super.setIncludePayload(true);
		super.setMaxPayloadLength(10000);
	}
	
}
