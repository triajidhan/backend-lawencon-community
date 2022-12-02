package com.lawencon.lawenconcommunity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;



@Configuration
public class SecurityConfig {

	@Bean
	public String[] getAllowedOrigins() {
		final String[] allowedOrigins = new String[] {"http://localhost:4200"};
		return allowedOrigins;
	}
	
	@Bean(name = "webIgnoring")
	public List<RequestMatcher> Matchers() {

		final List<RequestMatcher> requestMatchers = new ArrayList<>();
		requestMatchers.add(new AntPathRequestMatcher("/users/register/**", HttpMethod.POST.name()));
		requestMatchers.add(new AntPathRequestMatcher("/verification-code/**", HttpMethod.POST.name()));
		requestMatchers.add(new AntPathRequestMatcher("/industries/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/positions/**", HttpMethod.GET.name()));
		
		
		requestMatchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/v3/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
//		requestMatchers.add(new AntPathRequestMatcher("/reports/**", HttpMethod.GET.name()));
		return requestMatchers;
	}
}
