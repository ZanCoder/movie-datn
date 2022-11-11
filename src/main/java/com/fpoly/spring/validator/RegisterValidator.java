package com.fpoly.spring.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fpoly.spring.captcha.ICaptchaService;
import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;

@Component
public class RegisterValidator implements Validator {
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private AccountDAO accountDao;
	
	@Autowired
    private ICaptchaService captchaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == RegisterForm.class;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		RegisterForm registerForm = (RegisterForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.registerForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.registerForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.registerForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.registerForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recaptcha", "NotEmpty.registerForm.recaptcha");
		
		if (!this.emailValidator.isValid(registerForm.getEmail())) {
			errors.rejectValue("email", "Pattern.registerForm.email");
		} else {
			Account dbUser = accountDao.findByEmail(registerForm.getEmail());
			if (dbUser != null) {
				errors.rejectValue("email", "Duplicate.registerForm.email");
			}
		}
		
		if (!errors.hasFieldErrors("username")) {
			Account dbUser = accountDao.findByUsername(registerForm.getUsername());
			if (dbUser != null) {
				errors.rejectValue("username", "Duplicate.registerForm.username");
			}
		}
		
		if(registerForm.getConfirmPassword() != null) {
			if (!registerForm.getConfirmPassword().equals(registerForm.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.registerForm.confirmPassword");
			}
		}
        
	}
}
