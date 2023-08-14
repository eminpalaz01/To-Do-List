package com.user.springbootcase.business.abstracts;

import java.util.List;

import com.user.springbootcase.api.dto.PersonDto;
import com.user.springbootcase.core.utilities.concretes.DataResult;

public interface PersonService {

	DataResult<PersonDto> findByUsername(String username);

	DataResult<PersonDto> save(PersonDto personDto);

	DataResult<List<PersonDto>> findAll();

}
