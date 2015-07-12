package com.slgerkamp.bean.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ValidationTest {

	
	@RunWith(Theories.class)
	public static class 顧客のバリデーション{

		@DataPoints
		public static Fixture[] FIXTURES = {
			new Fixture(new Customer(true, false, 40), 0, "あなたはチャレンジ精神があり、車を持っていません。"),
			new Fixture(new Customer(false, true, 40), 2, "あなたはチャレンジ精神がなく、車を持っています。"),
			new Fixture(new Customer(true, false, -1), 1, "生まれてきたら登録ください。"),
			new Fixture(new Customer(true, false, 201), 1, "想定外の年齢です。")
		};
		
		@Theory
		public void チェック(Fixture f){

			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<Customer>> violations = validator.validate(f.customer);
			assertThat(f.description, violations.size(), is(f.violationSize));

			if (violations.size() > 0) {
				violations.stream().forEach(violation -> System.out.println(violation.getMessage()));				
			}
		}
	}
	
	
	
	static class Fixture {

		final Customer customer;
		final int violationSize;
		final String description;
		
		public Fixture(Customer customer, int violationSize, String description) {
			this.customer = customer;
			this.violationSize = violationSize;
			this.description = description;
		}

		@Override
		public String toString() {
			return String.format(
					"when customer.hasChallengeSpirit =%s, customer.hasCar =%s, customer.age =%s, " + 
					"violationSize=%s, description=%s", 
					customer.hasChallengeSpirit, customer.hasCar, customer.age, 
					violationSize, description);
		}
	}
}
