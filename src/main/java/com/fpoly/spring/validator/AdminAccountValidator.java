package com.fpoly.spring.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.form.AdminAccountForm;
import com.fpoly.spring.model.Account;

@Component
public class AdminAccountValidator implements Validator{
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private AccountDAO accountDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == AdminAccountForm.class;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		AdminAccountForm adminAccountForm = (AdminAccountForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.AdminAccountForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.AdminAccountForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.AdminAccountForm.password");
		
		if (!this.emailValidator.isValid(adminAccountForm.getEmail())) {
			errors.rejectValue("email", "Pattern.AdminAccountForm.email");
		} else {
			Account dbUser = accountDao.findByEmail(adminAccountForm.getEmail());
			if (dbUser != null) {
				errors.rejectValue("email", "Duplicate.AdminAccountForm.email");
			}
		}
		
		if (!errors.hasFieldErrors("username")) {
			Account dbUser = accountDao.findByUsername(adminAccountForm.getUsername());
			if (dbUser != null) {
				errors.rejectValue("username", "Duplicate.AdminAccountForm.username");
			}
		}
	}
}
