package com.robot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebMvcSecurity
@ComponentScan("com.robot.*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("diegoabv").password("diegoabv").roles("USER")
          .and()
          .withUser("user-unb").password("123789").roles("USER");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/login?expired").and().invalidSessionUrl("/login?expired").and()
		.authorizeRequests()
		.antMatchers("/login").anonymous()
		.antMatchers("/loginFail").anonymous()
		.antMatchers("/logout").anonymous()
		.antMatchers("/**").access("hasRole('ROLE_USER')")
		.antMatchers("/home**").access("hasRole('ROLE_USER')")
		//.anyRequest().authenticated()
		.and()
		  .formLogin().loginPage("/login").failureUrl("/login?error")
		  .defaultSuccessUrl("/home",true).usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf().disable();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**");
	}
	
	/*This is essential to make sure that the Spring Security session registry is notified when the session is destroyed*/
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
}
