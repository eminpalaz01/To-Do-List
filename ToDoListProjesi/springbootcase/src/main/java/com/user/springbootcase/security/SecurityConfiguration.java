package com.user.springbootcase.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.user.springbootcase.business.concretes.UserDetailsServiceImpl;
import com.user.springbootcase.dataAccess.PersonDao;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint handler;
	private final PersonDao personDao;
	
	@Autowired
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
			JwtAuthenticationEntryPoint handler,
			PersonDao personDao) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.handler = handler;
		this.personDao = personDao;
	}
	


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.cors().configurationSource(RequestBody -> {
			CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // React uygulamanızın adresi
            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfig.setAllowedHeaders(Arrays.asList("*"));
            return corsConfig;
		})
		.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/swagger-ui/**",
				     "/v3/**",
				     "/example/hello").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/auth/login").permitAll()
		.antMatchers("/api/v1/auth/register").permitAll()
//		.and()
//		.authorizeRequests()
//		.antMatchers("/**/admin/**").hasAnyAuthority("ADMIN")
		.and()
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.headers(headers -> headers.frameOptions().sameOrigin())
		.exceptionHandling().authenticationEntryPoint(handler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// TODO Auto-generated method stub
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		// buraya bak.
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(personDao); 
		
	}

}
