package com.fpoly.spring.utils;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.model.Account;

public class WebUtils {
	static String visa = "4687898882707317";
	static String mastercard = "5467348748950712";
	static String american_express = "379195325087295";
	static String discover = "6505231411996190";
	static String jcb = "3560625266009331";
	
	public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
	
	public static void main(String[] arg) {
		String password = "admin123";
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
	}
}
