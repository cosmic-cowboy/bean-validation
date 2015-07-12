package com.slgerkamp.bean.validation;

import javax.validation.constraints.AssertTrue;

public class Customer {

	@AssertTrue
	public final Boolean isExecutive;

	public Customer(Boolean isExecutive) {
		this.isExecutive = isExecutive;
	}
	
}
