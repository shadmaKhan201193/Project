package com.jbpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@SpringBootApplication

public class JbpmProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JbpmProjectApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
    	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    	RestTemplate rt = new RestTemplate();
    	rt.setRequestFactory( clientHttpRequestFactory);
        return rt;
	}

	
	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
		return objectMapper;
	}
	
	
	


}
