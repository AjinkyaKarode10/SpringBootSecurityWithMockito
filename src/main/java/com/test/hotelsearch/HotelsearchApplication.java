package com.test.hotelsearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class HotelsearchApplication {

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		System.out.println(list.stream().map(x -> x * x).reduce((x, y) -> x + y).get());
		
		List<String> list1 = Arrays.asList(1, 2, 3).stream()             // Stream<Integer>
	    .map(s -> String.valueOf(s)).collect(Collectors.toList());
		
		System.out.println(list1);
		
		//SpringApplication.run(HotelsearchApplication.class, args);
	}

}
