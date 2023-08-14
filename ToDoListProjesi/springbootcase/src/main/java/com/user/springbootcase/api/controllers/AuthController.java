package com.user.springbootcase.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.springbootcase.api.dto.AuthenticateRequest;
import com.user.springbootcase.api.dto.PersonDto;
import com.user.springbootcase.business.abstracts.PersonService;
import com.user.springbootcase.business.concretes.PersonManager;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.core.utilities.concretes.TokenDataResult;
import com.user.springbootcase.dataAccess.PersonDao;
import com.user.springbootcase.security.JwtUserDetails;
import com.user.springbootcase.security.JwtUtils;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final PersonDao personDao;
	private final JwtUtils jwtUtils;
	private PersonService personService;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, PersonDao personDao, JwtUtils jwtUtils,
			PersonManager maxUserService) {
		this.authenticationManager = authenticationManager;
		this.personDao = personDao;
		this.jwtUtils = jwtUtils;
		this.personService = maxUserService;
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDataResult<String>> authenticate(@RequestBody AuthenticateRequest request) {
		Authentication auth =authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken
						(request.getUsername(), request.getPassword()));
	 final UserDetails user = JwtUserDetails.create(personDao.findByUsername(request.getUsername()));
     if(user != null) {
    	 SecurityContextHolder.getContext().setAuthentication(auth);
    	 return ResponseEntity.ok(new TokenDataResult<String>(jwtUtils.generateToken(user), true, "Giriş işlemi başarılı"));
     }
     
      return ResponseEntity.ok(new TokenDataResult<String>(false, "Kullanıcı bilgilerinizi kontrol ediniz."));

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody PersonDto personDto) {

		DataResult<PersonDto> userAns = personService.save(personDto);
		if (userAns.isSuccess()) {
			return new ResponseEntity<>(userAns.getMessage(), HttpStatus.CREATED);
		} 	
		
		return new ResponseEntity<>(userAns.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
