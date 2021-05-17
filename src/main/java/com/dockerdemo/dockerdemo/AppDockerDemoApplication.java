package com.dockerdemo.dockerdemo;

import com.dockerdemo.dockerdemo.security.MyBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@SpringBootApplication
@Configuration
public class AppDockerDemoApplication {

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	public static void main(String[] args) {
		SpringApplication.run(AppDockerDemoApplication.class, args);
	}

	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.csrf().disable()
			.authorizeRequests()
			//.antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);

		//http.addFilterAfter(new CustomFilter(),
		//		BasicAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder().encode("123"))
				.roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
