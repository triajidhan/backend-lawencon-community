package com.lawencon.lawenconcommunity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@Configuration
public class SecurityConfig {

	@Bean(name = "webIgnoring")
	public List<RequestMatcher> Matchers() {

		final List<RequestMatcher> requestMatchers = new ArrayList<>();
		requestMatchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.POST.name()));
//		requestMatchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.PUT.name()));
		requestMatchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/v3/**", HttpMethod.GET.name()));
		requestMatchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
		return requestMatchers;
	}
}
