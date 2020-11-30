package com.test.hotelsearch.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(HotelNameInvalidException.class)
    public ResponseEntity<Object> handleHotelNameInvalidException(HotelNameInvalidException ex)
    {
    	ApiResponse responseDTO = new ApiResponse(HttpStatus.FORBIDDEN.value(), "FAILURE");
    	responseDTO.setMessage(ex.getMessage());
    	return new ResponseEntity<Object>(responseDTO,HttpStatus.BAD_REQUEST);
    	
       
    }
	
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, HttpServletResponse response)
    {
    	ApiResponse responseDTO = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Access denied");
    	responseDTO.setMessage(ex.getMessage());
    	return new ResponseEntity<Object>(responseDTO,HttpStatus.BAD_REQUEST);
    	
       
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletResponse response)
    {
    	ApiResponse responseDTO = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Access denied");
    	responseDTO.setMessage(ex.getMessage());
    	return new ResponseEntity<Object>(responseDTO,HttpStatus.BAD_REQUEST);
    	
       
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletResponse response)
    {
    	System.out.println("Inside @ExceptionHandler ");
    	ApiResponse responseDTO = new ApiResponse(HttpStatus.FORBIDDEN.value(), "FAILURE");
    	responseDTO.setMessage(ex.getMessage());
    	return new ResponseEntity<Object>(responseDTO,HttpStatus.BAD_REQUEST);
    	
       
    }
    
    
}