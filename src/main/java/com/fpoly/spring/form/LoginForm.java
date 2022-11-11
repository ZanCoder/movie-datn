package com.fpoly.spring.form;

public class LoginForm {
	String email;
	String password;
	Boolean rememberme;
	String recaptcha;
	
	public LoginForm() {
		super();
	}

	public LoginForm(String email, String password, Boolean rememberme, String recaptcha) {
		super();
		this.email = email;
		this.password = password;
		this.rememberme = rememberme;
		this.recaptcha = recaptcha;
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

	public Boolean getRememberme() {
		return rememberme;
	}

	public void setRememberme(Boolean rememberme) {
		this.rememberme = rememberme;
	}

	public String getRecaptcha() {
		return recaptcha;
	}

	public void setRecaptcha(String recaptcha) {
		this.recaptcha = recaptcha;
	}
	
	
}
