package com.in28minutes.rest.webservices.restfulwebservices.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
            .authorizeHttpRequests((authz) -> authz
//                  .requestMatchers("/h2-console/**").permitAll()
//                  .requestMatchers("/users/**").permitAll()
                  .requestMatchers("/jpa/**").permitAll()
                  .anyRequest().authenticated()
            )
            .formLogin(form -> form
                  .loginPage("/login")
                  .permitAll()
            )
            .csrf(csrf -> csrf
                  .ignoringRequestMatchers("/jpa/**")
//                  .ignoringRequestMatchers("/h2-console/**")
            )
            .headers( headers -> headers.frameOptions( frameOptionsConfig-> frameOptionsConfig.disable() )
      );

      return http.build();
   }

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
////		1) All requests should be authenticated
//		http.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated()
//				);
////		2) If a request is not authenticated, use http basic
//		http.httpBasic(withDefaults());
//
////		3) CSRF -> POST, PUT
//	         http.csrf(csrf -> csrf.disable());
	      // OR
//		http.csrf(AbstractHttpConfigurer::disable);
//
//
//		return http.build();
//	}

}
