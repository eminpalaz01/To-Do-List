package com.user.springbootcase.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.user.springbootcase.dataAccess.PersonDao;
import com.user.springbootcase.entities.Person;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private PersonDao personDao;
    private JwtUtils jwtUtils;
    
    @Autowired
	public JwtAuthenticationFilter(PersonDao personDao, JwtUtils jwtUtils) {
		this.personDao = personDao;
		this.jwtUtils = jwtUtils;
	}
    
    
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		final String username;
		final String jwtToken;
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwtToken = authHeader.substring(7);
		// Debugta bakıldı token ile usernami getiriyor sorun yok.
		username = jwtUtils.extractUserName(jwtToken);
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			Person person = personDao.findByUsername(username);
			UserDetails userDetails = JwtUserDetails.create(person);
			
			if(jwtUtils.isTokenValid(jwtToken, userDetails)){
				// isTokenValidimi true döndürdü debugta ve aşağıya geldi şimdi burayı test edicez
                // userDetails nesnemdeki veriler db deki ile eşleşti. 
				// debug ile derine in ve hata sebebini bul.
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
						(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
