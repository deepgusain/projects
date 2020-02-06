package com.deep.study.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtAuthenticatedToken implements Authentication {

	private static final long serialVersionUID = 4547413450356171139L;

	private String token;
	private String principal;
	private String credentials;
	private boolean authenticated;

	public JwtAuthenticatedToken(String token, String principal, String credentials, boolean isAuthenticated) {
		this.token = token;
		this.principal = principal;
		this.credentials = credentials;
		this.authenticated = isAuthenticated;
	}

	@Override
	public String getName() {
		return this.principal;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		return this.token;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated){
		
		/*
		 * Removed the (IllegalArgumentException) from the method declaration here
		 */
	}

}
