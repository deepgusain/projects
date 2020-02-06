package com.deep.study.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
@Component
public class JwtAuthenticationFilter extends GenericFilterBean{

	@Autowired
	TokenService tokenService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorization = servletRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ") ) {
            
        	boolean isValidToken = tokenService.validateToken(authorization);
        	if(isValidToken){
            	JwtAuthenticatedToken authentication = new JwtAuthenticatedToken(authorization, "user", null, true);
                SecurityContextHolder.getContext().setAuthentication(authentication);        		
        	}else{
        		LOGGER.error("Error Validating the token for request {}",((HttpServletRequest)request).getRequestURL());
        	}
        }else{
        	SecurityContextHolder.getContext().setAuthentication(null);
        }
        
        chain.doFilter(request, response);    
}

	
}
