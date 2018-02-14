package com.naga.spring.rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		context.setServletContext(servletContext);
		
		javax.servlet.ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		
		servletContext.addFilter("customRequestLoggingFilter", 
				CommonsRequestLoggingFilter.class).addMappingForServletNames(null, false, "dispatcher");
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
	    crlf.setIncludeClientInfo(true);
	    crlf.setIncludeQueryString(true);
	    crlf.setIncludePayload(true);
	    return crlf;
	}

}
