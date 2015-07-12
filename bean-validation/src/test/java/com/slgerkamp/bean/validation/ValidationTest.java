package com.slgerkamp.bean.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class ValidationTest {

	@Test
	public void エグゼクティブ以外は参加できない(){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Customer customer = new Customer(false);
		
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertThat("あなたはエグゼクティブではないです。", violations.size(), is(1));
		
		violations.stream().forEach(violation -> System.out.println(violation.getMessage()));
	}
	
	@Test
	public void エグゼクティブが参加する(){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Customer customer = new Customer(true);
		
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertThat("あなたはエグゼクティブです。", violations.size(), is(0));
		
	}

}
