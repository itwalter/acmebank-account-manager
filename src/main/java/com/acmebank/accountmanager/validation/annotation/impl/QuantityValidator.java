package com.acmebank.accountmanager.validation.annotation.impl;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.acmebank.accountmanager.validation.annotation.ValidQuantity;

public class QuantityValidator implements ConstraintValidator<ValidQuantity, BigDecimal> {
	
	public void initialize(ValidQuantity constraint) {
		
	}

	@Override
	public boolean isValid(BigDecimal quantity, ConstraintValidatorContext context) {
		if (quantity != null && quantity.compareTo(new BigDecimal("0")) > 0) {
			return true;
		}
		return false;
	}

}
