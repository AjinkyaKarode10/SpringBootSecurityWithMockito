package com.test.hotelsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import com.test.hotelsearch.exception.CustomAccessDeniedHandler;
import com.test.hotelsearch.exception.CustomAuthenticationResponseHandler;
import com.test.hotelsearch.exception.RestAuthenticationFailureHandler;
import com.test.hotelsearch.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	CustomAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	CustomAuthenticationResponseHandler authenticationHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				//.authorizeRequests().antMatchers("/authenticate", "/register").permitAll()
				.authorizeRequests().antMatchers("/hello","/hotel/**").permitAll()
				// all other requests need to be authenticated
				//.anyRequest().authenticated()
				//.and().authorizeRequests().antMatchers("/hasAdminPermission").access("hasAuthority('CREATE')").and()
				.and().authorizeRequests().antMatchers("/admin/**").authenticated().and().httpBasic().and()
				// make sure we use stateless session; session won't be used to
				// store user's state.
				//accessDeniedHandler(accessDeniedHandler())
				.exceptionHandling().authenticationEntryPoint(authenticationHandler).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		//httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		//httpSecurity.addFilterBefore(authenticationFailureHandler(), AnonymousAuthenticationFilter.class);
	}
	
//	@Bean
//    RestAuthenticationFailureHandler authenticationFailureHandler(){
//		final AnonymousAuthenticationFilter filter = new AnonymousAuthenticationFilter();
//        return new RestAuthenticationFailureHandler();
//    }
	
}
