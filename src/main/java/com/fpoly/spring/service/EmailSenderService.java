package com.fpoly.spring.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMailHtml(String toEmail, String subject, String body) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, false));
		mimeMessage.setSubject(subject);
		mimeMessage.setContent(body, "text/html; charset=utf-8");
		
		javaMailSender.send(mimeMessage);
	}
	
	public void sendMail(String toEmail, String subject, String body) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(body);
		
		javaMailSender.send(mimeMessage);
	}
}
