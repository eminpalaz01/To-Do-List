package com.user.springbootcase.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.springbootcase.api.dto.PersonDto;
import com.user.springbootcase.business.abstracts.PersonService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.security.JwtUtils;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	private final JwtUtils jwtUtils;
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService, JwtUtils jwtUtils) {
		this.personService = personService;
		this.jwtUtils = jwtUtils;
	}

	@GetMapping
	public ResponseEntity<DataResult<List<PersonDto>>> findAll() {
		return ResponseEntity.ok(personService.findAll());

	}

	@GetMapping("/me")
	public ResponseEntity<DataResult<PersonDto>> findByToken(
			@RequestHeader("Authorization") String authorizationHeader) {
		String jwtToken = authorizationHeader.substring(7);
		String username = jwtUtils.extractUserName(jwtToken);

		DataResult<PersonDto> personDtoDataResult = personService.findByUsername(username);

		if (personDtoDataResult.getData() != null) {
			personDtoDataResult.setMessage("Tokenın ait olduğu kullanıcı getirildi.");
			return ResponseEntity.ok(personDtoDataResult);
		}

		// Buraya gelmeyecek çünkü securityConfigte burada tokenı istiyor zaten 
		// sadece okunurluk açısından ekledim.
		personDtoDataResult.setMessage("Tokenınızı kontrol ediniz.");
		return ResponseEntity.ok(personDtoDataResult);
	}

}
