package com.fpoly.spring.captcha;

public interface  ICaptchaService {
	default void processResponse(final String response) {}
    
    default void processResponse(final String response, String action) {}
    
    String getReCaptchaSite();

    String getReCaptchaSecret();
}
