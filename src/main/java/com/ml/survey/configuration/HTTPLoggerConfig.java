package com.ml.survey.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * This Class enable HTTP logging.
 * 
 * @author Kaustuv Maji
 */
@Configuration
public class HTTPLoggerConfig {

	/**
	 * init HTTP logging bean.
	 * @return
	 */
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(64000);
	    return loggingFilter;
	}
}
