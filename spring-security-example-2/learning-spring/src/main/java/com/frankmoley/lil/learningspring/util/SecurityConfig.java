package com.frankmoley.lil.learningspring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.frankmoley.lil.learningspring.SwaggerConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	/*
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
          .and()
          .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("123").roles("APPRENTICE")
                .and()
                .withUser("user2").password("123").roles("SENSEI");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("*")
		.permitAll()
		.and()
		.httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.
				withDefaultPasswordEncoder()
		 		.username("user")
		.password("admin")
		.roles("USER")
		.build();
	 	return new InMemoryUserDetailsManager(user);
	}	
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailService() {
		UserDetails user = User.withUsername("admin")
				.password("admin")
				.roles("USER")
				.build();
		UserDetails user2 = User.withUsername("user2")
	            .password(passwordEncoder().encode("user2Pass"))
	            .roles("USER")
	            .build();
        UserDetails admin = User.withUsername("admin")
	            .password(passwordEncoder().encode("adminPass"))
	            .roles("ADMIN")
	            .build();
		return new InMemoryUserDetailsManager(user,user2,admin);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.csrf().disable().authorizeRequests()
		.antMatchers("/admin")
		.hasRole("ADMIN")
		.antMatchers("anonymous")
		.permitAll()        
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("login.html")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/homepage")
		.failureUrl("error")
		.and().logout()
		.logoutUrl("logout")
		.deleteCookies("JSSESIONID");
		 return http.build();
		
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}*/
	@Bean
	public SwaggerConfig getSwagger() {
		return null;
	}
}
