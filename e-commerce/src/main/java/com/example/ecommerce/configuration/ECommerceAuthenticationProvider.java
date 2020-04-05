package com.example.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ECommerceAuthenticationProvider extends DaoAuthenticationProvider {
	private static final int MAX_LOGIN_ATTEMPT = 3;
	@Autowired
	UserDao userDao;
	
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		try {
			Authentication auth = super.authenticate(authentication);
			userDao.setLoginAttempt(authentication.getName(), 0);
			return auth;
		} catch (AuthenticationException e) {
			Integer loginAttempt = userDao.getLoginAttempt(authentication.getName());
			if (loginAttempt == null || loginAttempt >= MAX_LOGIN_ATTEMPT) {
				throw new AccountLockedException(e);
			}
			userDao.setLoginAttempt(authentication.getName(), ++loginAttempt);
			throw e;
		}
	}
	
	public static class AccountLockedException extends AuthenticationException {

		public AccountLockedException(Throwable t) {
			super("User Account has been locked, please reset the password", t);
		}
		
		public AccountLockedException(String msg, Throwable t) {
			super(msg, t);
		}
		
	}
}
