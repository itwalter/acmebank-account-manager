package com.acmebank.accountmanager.validation.annotation.impl;

import com.acmebank.accountmanager.validation.annotation.ValidAccount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<ValidAccount, Long> {
	
	public void initialize(ValidAccount constraint) {
		
	}

	@Override
	public boolean isValid(Long account, ConstraintValidatorContext context) {
		if (account != null && account > 0L) {
			return true;
		}
		return false;
	}

}
