package com.fpoly.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.Account_RoleDAO;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Account_Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AccountDAO accountDao;

	@Autowired
	Account_RoleDAO account_roleDao;

	@Override
	public UserDetails loadUserByUsername(String emailAre) throws UsernameNotFoundException {
		if (emailAre.split(",").length < 2) {
			throw new UsernameNotFoundException("Recaptcha is required ");
		}

		String email = emailAre.split(",")[0];
		String recaptcha = emailAre.split(",")[1];

		Account account = accountDao.findByEmail(email);

		if (account == null) {
			System.out.println("User not found! " + account);
			throw new UsernameNotFoundException("User " + account + " was not found in the database");
		}else if(account.getStatus() == false) {
			throw new UsernameNotFoundException("Account has been banned");
		}

		System.out.println("Found User: " + account.getUsername());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().getAcc_role_name());
		grantList.add(authority);

		UserDetails userDetails = (UserDetails) new User(account.getEmail(), account.getPassword_hash(), grantList);

		return userDetails;
	}
}
