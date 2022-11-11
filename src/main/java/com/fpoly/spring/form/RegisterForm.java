package com.fpoly.spring.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterForm {
	String username;
	String email;
	String password;
	String confirmPassword;
	String recaptcha;
	
	public RegisterForm() {}

	public RegisterForm(String recaptcha, String username, String email, String password, String confirmPassword) {
		super();
		this.recaptcha = recaptcha;
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	

	public String getRecaptcha() {
		return recaptcha;
	}

	public void setRecaptcha(String recaptcha) {
		this.recaptcha = recaptcha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
