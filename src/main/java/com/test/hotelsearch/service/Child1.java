package com.test.hotelsearch.service;

import org.springframework.stereotype.Component;

@Component
public class Child1 extends Parent{

	@Override
	public void abs() {
		System.out.println("In child1 abs");
		
	}

	@Override
	public void nonAbstract()
	{
		System.out.println("In Child1 nonabs");
	}
	
}
