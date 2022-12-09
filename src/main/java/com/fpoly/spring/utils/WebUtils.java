package com.fpoly.spring.utils;

import java.io.Serializable;
import java.security.Key;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	
	public static void main(String[] arg) throws ParseException {
		String password = "phat";
        String encrytedPassword = encrytePassword(password);
        System.out.println("Encryted Password: " + encrytedPassword);
	}
}
