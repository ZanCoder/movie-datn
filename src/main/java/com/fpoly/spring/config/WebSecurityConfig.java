package com.fpoly.spring.config;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.fpoly.spring.handler.AuthFailureHandler;
import com.fpoly.spring.handler.AuthSuccessHandler;
import com.fpoly.spring.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    AuthFailureHandler authFailureHandler;

    @Autowired
    AuthSuccessHandler authSuccessHandler;
    
    @Autowired
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		
		http.authorizeRequests().antMatchers(
				"/user/profile", "/user/continue-watching", "/user/watch-list", "/user/notification", "/user/purchase-history"
			).authenticated();
		
		http.authorizeRequests().antMatchers("/admin/dashboard",
											"/admin/notification-movie-form",
											"/admin/transaction-history-table",
											"/admin/movie-form", "/admin/movie-table", 
											"/admin/movie-ep-form", "/admin/movie-ep-table",
											"/admin/revenue-chart", "/admin/sales-chart", "/admin/purchase-chart")
		.hasAnyAuthority("ADMIN", "MOD");
		http.authorizeRequests().antMatchers("/admin/account-form", "/admin/account-table", 
				"/admin/authorize-table")
		.hasAnyAuthority("ADMIN");
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");
		
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/")
			.failureHandler(authFailureHandler)
            .successHandler(authSuccessHandler)
			.usernameParameter("emailAre")
			.passwordParameter("password")
			.and()
			.rememberMe().rememberMeParameter("remember_me")
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(86400*30)
			.and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID", "remember-me")
			.logoutSuccessUrl("/home");
		
//		http.authorizeRequests().and() 
//		.rememberMe().rememberMeParameter("remember_me")
//		.tokenRepository(persistentTokenRepository())
//		.tokenValiditySeconds(86400*30);
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); 
        return memory;
    }
	
	@Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices service = new PersistentTokenBasedRememberMeServices("remember-me", userDetailsService, persistentTokenRepository());
        service.setCookieName("remember-me");
        service.setTokenValiditySeconds(864000);
        return service;
    }
}
