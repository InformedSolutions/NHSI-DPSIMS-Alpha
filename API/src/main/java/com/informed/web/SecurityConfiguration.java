package com.informed.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.informed.service.ApiKeyService;

/**
 * Custom security configuration for rest-based microservices to enforce basic
 * authentication that calls a custom userDetailsService implementation.
 * 
 * @author James Cruddas
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// @Autowired annotation used to support Spring DI (apiClientService is
	// injected at run-time)
	/**
	 * A concrete implementation of an API client service.
	 */
	@Autowired
	private ApiKeyService apiClientService;

	/**
	 * Configuration setting value on whether Spring security is enabled.
	 */
	@Value("${security.basic.enabled}")
	private boolean securityEnabled;

	/**
	 * Override method for attaching the apiClientService instance to Spring's
	 * auth.userDetailsService instance.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(apiClientService);
	}

	/**
	 * Override method for configuring Spring security settings.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (securityEnabled) {
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					.anyRequest().fullyAuthenticated();

			http.httpBasic();

			http.csrf().disable();
		}
	}

}