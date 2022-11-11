package com.fpoly.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.spring.captcha.ICaptchaService;
import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.form.LoginForm;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.response.ValidateResponse;
import com.fpoly.spring.service.EmailSenderService;
import com.fpoly.spring.validator.RegisterValidator;

@Controller
public class LoginController {
	
	@Autowired
	private RegisterValidator registerValidator;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountDAO accountDao;
	
	@Autowired
    private ICaptchaService captchaService;
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		
		System.out.println("Target=" + target);

		if (target.getClass() == RegisterForm.class) {
			dataBinder.setValidator(registerValidator);
		}
	}
	
	@RequestMapping(value="/register", produces={MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse saveRegister(@Valid @RequestBody RegisterForm registerForm, BindingResult result, HttpServletRequest request) {
		
		ValidateResponse response = new ValidateResponse();
		
		if (result.hasErrors()) {
			List<FieldError> errorLists = result.getFieldErrors();
			Map<String, String> errorMaps = new HashMap();
			errorLists.forEach((error) -> errorMaps.put(error.getField(), messageSource.getMessage(error.getCode(), null, Locale.ENGLISH) ));
			
			response.setValidated(false);
            response.setErrorMessages(errorMaps);
            
            System.out.println(errorLists);
			
		}else {
	        Account newUser = new Account();
			try {
				String encrytedPassword = this.passwordEncoder.encode(registerForm.getPassword());
				
				newUser.setUsername(registerForm.getUsername());
				newUser.setEmail(registerForm.getEmail());
				newUser.setPassword_hash(encrytedPassword);
				
				accountDao.createAccount(newUser);
				
				response.setValidated(true);
			}
			catch (Exception e) {
				response.setValidated(false);
				response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", "Some thing wrong!");
	            }});
			}
			
		}
		
		return response;
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse newPass(HttpServletRequest request) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 15, characters );
		
		String email = request.getParameter("email");
		String recaptcha = request.getParameter("recaptcha");

		ValidateResponse response = new ValidateResponse();
		
		Account account = accountDao.findByEmail(email);
		
		System.out.println(email);
		
		if(account == null) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "No found email.");
            }});
		}else if(recaptcha == "") {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Recaptcha is required.");
            }});
		}
		else {
			try {
				String encrytedPassword = this.passwordEncoder.encode(pwd);
				
				accountDao.changePassword(account.getId(), encrytedPassword);
				
				emailSenderService.sendMail(account.getEmail(), "New password", pwd);
				
				response.setValidated(true);
			}
			catch (Exception e) {
				response.setValidated(false);
	            response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", "Some thing wrong!");
	            }});
			}
		}
		
		return response;
	}
}
