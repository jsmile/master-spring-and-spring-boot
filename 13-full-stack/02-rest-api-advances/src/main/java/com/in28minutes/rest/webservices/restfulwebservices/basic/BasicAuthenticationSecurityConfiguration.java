package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	//Filter chain
	// authenticate all requests
	//basic authentication
	//disabling csrf
	//stateless rest api
	
/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//1: Response to preflight request doesn't pass access control check
		//2: basic auth
		return 
				http
					.authorizeHttpRequests(
						auth -> 
							auth
							.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//							.requestMatchers("/**").permitAll()
							.anyRequest().authenticated()
						)
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(
						session -> session.sessionCreationPolicy
						(SessionCreationPolicy.STATELESS))   // 클라이언트는 매 요청마다 인증 정보를 제공해야 함.
						// .csrf().disable() Deprecated in SB 3.1.x
					.csrf(csrf -> csrf.disable()) // Starting from SB 3.1.x using Lambda DSL
			     // .csrf(AbstractHttpConfigurer::disable) // Starting from SB 3.1.x using Method Reference
					.build();
	}
*/

/* comments out Basic Authentication
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(
				auth ->
						auth.anyRequest()
								.authenticated()   // 모든 요청 인증 검사
		)
		.httpBasic( withDefaults() )         // 인증 검사 시 login diralog 사용
		.csrf( csrf -> csrf.disable() )     // GET 이외의 작업 시 필요한 CSRF 검사 비활설와
		.sessionManagement(
				// 클라이언트는 매 요청마다 인증 정보를 제공해야 함.
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);

		return http.build();
	}
*/

}
