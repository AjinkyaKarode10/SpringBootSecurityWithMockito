package com.test.hotelsearch.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.test.hotelsearch.entity.Role;
import com.test.hotelsearch.entity.User;

//@TestConfiguration
	public class SpringSecurityWebTestConf {

//	    @Bean
//	    @Primary
//	    public UserDetailsService userDetailsService() {
//	        
//	        User basicActiveUser = new User();
//	        basicActiveUser.setUsername("Ajinkya");
//	        basicActiveUser.setPassword("");
//	        basicActiveUser.setEmail("ak@gmail.com");
//	        basicActiveUser.setRoles(new HashSet<Role>());

//	        User managerUser = new UserImpl("Manager User", "manager@company.com", "password");
//	        UserActive managerActiveUser = new UserActive(managerUser, Arrays.asList(
//	                new SimpleGrantedAuthority("ROLE_MANAGER"),
//	                new SimpleGrantedAuthority("PERM_FOO_READ"),
//	                new SimpleGrantedAuthority("PERM_FOO_WRITE"),
//	                new SimpleGrantedAuthority("PERM_FOO_MANAGE")
//	        ));

	       // return new InMemoryUserDetailsManager((Properties) Arrays.asList(basicActiveUser));
	    //}
	}

