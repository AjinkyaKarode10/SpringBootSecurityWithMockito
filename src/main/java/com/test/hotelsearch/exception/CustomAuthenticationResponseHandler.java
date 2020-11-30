package com.test.hotelsearch.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationResponseHandler implements  AuthenticationEntryPoint  {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("Inside @CustomAuthenticationResponseHandler ");
		ApiResponse apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorised");
		apiResponse.setMessage("Please provide correct credentials");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiResponse);
        out.flush();
		
	}
	

	
	
}
