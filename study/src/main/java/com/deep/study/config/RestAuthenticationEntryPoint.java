package com.deep.study.config;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {
		// This is invoked when user tries to access a secured REST resource
		// without supplying any credentials
		// We should just send a 401 Unauthorized response because there is no
		// 'login page' to redirect to

		boolean isAuthenticated = evaluateIfRequestIsAjax(request);

		if (isAuthenticated) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			byte[] body = new ObjectMapper().writeValueAsBytes("UnAuthorize");
			response.getOutputStream().write(body);

		} else {
			response.sendRedirect("/");
		}

	}

	private boolean evaluateIfRequestIsAjax(HttpServletRequest request) {
		boolean isAuthenticated = false;
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				if ("Authorization".equals(name)) {
					isAuthenticated = true;
				}
			}
		return isAuthenticated;
	}
}
