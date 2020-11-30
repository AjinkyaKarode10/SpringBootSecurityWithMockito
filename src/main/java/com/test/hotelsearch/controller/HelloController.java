package com.test.hotelsearch.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping
public class HelloController {
	@Value("${hello.message}")
	private String helloMessage;
	
	@GetMapping(value = "/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok(helloMessage);
	}
	
	@PostMapping(value = "/hasAdminPermission")
	@PreAuthorize("hasAuthority('UPDATE')")
	public ResponseEntity<String> hasAdminPermission() {
		System.out.println("Inside hasAdminPermission");
		return ResponseEntity.ok(helloMessage);
	}
}
