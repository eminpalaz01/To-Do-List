package com.user.springbootcase.core.utilities.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.springbootcase.core.utilities.abstracts.UserInputValidationService;
import com.user.springbootcase.dataAccess.PersonDao;

@Service
public class UserInputValidationManager implements UserInputValidationService {

	@Autowired
	private PersonDao personDao;
	
	public boolean usernameCheck(String username) {
		// TODO Auto-generated method stub
		if(personDao.existsByUsername(username)) {
		   return false;	
		} 
        return true;
	}

	@Override
	public boolean passwordCheck(String password) {
		// TODO Auto-generated method stub
		if (password.length() >= 8 && password.length() <= 16) {
			return true;
		}
		return false;
	}
}
