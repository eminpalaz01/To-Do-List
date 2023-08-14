package com.user.springbootcase.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
		/* Exception handlerdan geri dönen isteğin bilgileri OpenApi de
		 * izin verilmesi gerekn url'ler için kullandım
		 * 
		System.out.println(request.getRequestURI());
		System.out.println(authException.getMessage());
		
		*/
	}

}
