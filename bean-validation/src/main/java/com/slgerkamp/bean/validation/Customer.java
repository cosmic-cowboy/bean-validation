package com.slgerkamp.bean.validation;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Customer {

	@AssertTrue
	public final Boolean hasChallengeSpirit;

	@AssertFalse
	public final Boolean hasCar;

	@Min(0)
	@Max(200)
	public final int age;
  
	public Customer(Boolean hasChallengeSpirit, Boolean hasCar, int age) {
		this.hasChallengeSpirit = hasChallengeSpirit;
		this.hasCar = hasCar;
		this.age = age;
	}
	
}
