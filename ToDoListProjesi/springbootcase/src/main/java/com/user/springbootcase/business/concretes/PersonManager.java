package com.user.springbootcase.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.springbootcase.api.dto.PersonDto;
import com.user.springbootcase.business.abstracts.PersonService;
import com.user.springbootcase.core.utilities.abstracts.UserInputValidationService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.dataAccess.PersonDao;
import com.user.springbootcase.entities.Person;

@Service
public class PersonManager implements PersonService {

	private final PersonDao personDao;
	private final UserInputValidationService userInputValidationService;
	private final PasswordEncoder passwordEncoder;

	public PersonManager(PersonDao personDao, UserInputValidationService userInputValidationService,
			PasswordEncoder passwordEncoder) {
		this.personDao = personDao;
		this.userInputValidationService = userInputValidationService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public DataResult<PersonDto> findByUsername(String username) {
		// TODO Auto-generated method stub
		try {
			Person person = personDao.findByUsername(username);
			PersonDto personDto = new PersonDto();

			if (person != null) {
				personDto.setUsername(person.getUsername());
				personDto.setPassword(person.getPassword());
				personDto.setRole(person.getRole());
				return new DataResult<PersonDto>(personDto, true, username + " kullancı adlı kişi getirildi.");
			}

			return new DataResult<PersonDto>(false, username + " kullancı adlı kişi bulunamadı.");
		} catch (Exception e) {
			// TODO: handle exception
			return new DataResult<PersonDto>(false, username + e.getMessage());

		}

	}

	@Override
	public DataResult<PersonDto> save(PersonDto personDto) {
		// TODO Auto-generated method stub
		try {
			boolean usernameValid = userInputValidationService.usernameCheck(personDto.getUsername());
			boolean passwordValid = userInputValidationService.passwordCheck(personDto.getPassword());
			
			if (usernameValid && passwordValid){
					 
				Person person = new Person();

				person.setUsername(personDto.getUsername());
				// Şifreleme yapılacak burada
				person.setPassword(passwordEncoder.encode(personDto.getPassword()).toString());
				person.setRole("USER");
		
				personDao.save(person);

				return new DataResult<PersonDto>(personDto, true, "Kullanıcı Veritabanına eklendi.");
			}

			if (!(usernameValid)) {
				return new DataResult<PersonDto>(false, "Kullanıcı adı kullanılmaktadır.");
			}

			if (!(passwordValid)) {
				return new DataResult<PersonDto>(false, "Şifreler minimum 8 karakter maksimum 16 karakter olabilir.");
			}

			return new DataResult<PersonDto>(false, "Bir sorun oluştu işlem başarısız.");
		} catch (Exception e) {
			// TODO: handle exception
			return new DataResult<PersonDto>(false, e.getMessage());
		}

	}

	public DataResult<List<PersonDto>> findAll() {
		try {
			List<PersonDto> usersDto = new ArrayList<>();
			List<Person> persons = personDao.findAll();

			if(persons.size() != 0) {
				persons.forEach(user -> {
					PersonDto personDto = new PersonDto();
					personDto.setUsername(user.getUsername());
					personDto.setPassword(user.getPassword());
					personDto.setRole(user.getRole());
					usersDto.add(personDto);
				});
			} else {
				return new DataResult<List<PersonDto>>(false, "Kayıtlı kullanıcı bulunamadı.");
			}
			return new DataResult<List<PersonDto>>(usersDto, true, "Kayıtlı kullanıcılar getirildi.");
		} catch (Exception e) {
			// TODO: handle exception
			return new DataResult<List<PersonDto>>(false, e.getMessage());
		}

	}

}
