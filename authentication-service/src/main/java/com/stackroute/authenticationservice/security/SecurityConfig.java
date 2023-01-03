package com.stackroute.authenticationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  	 {


	/*@Autowired
	private CustomUserDetailsService userDetailsService;

	*//**
	 * JWT
	 *//*
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}


	*//**
	 * Authorization
	 *//*
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				.exceptionHandling() //jwt
				.authenticationEntryPoint(authenticationEntryPoint) //jwt
				.and() //jwt
				.sessionManagement() //jwt
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt
				.and() //jwt
				.authorizeRequests()
				.antMatchers("/authentication/**").permitAll()
				.antMatchers("/swagger-ui/**", "/authentication/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // jwt
		//	.and();
		//	.httpBasic(); we are jwt authentication
	}





	*//**
	 * In Memory Authentication
	 *
	 * If you don't encode password, login will fail

	 /**
	 * Authentication
	 *
	 *
	 *//*


	//In memory authentication
*//*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("javainuse")
				.password(passwordEncoder().encode("javainuse"))
				.authorities("ADMIN");
	}*//*


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
*/


	public static final String[] PUBLIC_URLS={
					"/authentication/**",
					"/swagger-resources/**",
					"/swagger-ui/**"
			};
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Bean
	public JwtAuthenticationFilter authenticationJwtTokenFilter() {
		return new JwtAuthenticationFilter();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(PUBLIC_URLS)
				.permitAll()
				.anyRequest()
				.authenticated();
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
