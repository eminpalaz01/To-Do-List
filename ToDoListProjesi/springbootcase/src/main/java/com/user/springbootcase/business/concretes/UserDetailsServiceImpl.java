package com.user.springbootcase.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.springbootcase.dataAccess.PersonDao;
import com.user.springbootcase.entities.Person;
import com.user.springbootcase.security.JwtUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	private PersonDao personDao;
	
	@Autowired
	public UserDetailsServiceImpl(PersonDao personDao) {
		this.personDao = personDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Person person = personDao.findByUsername(username);
		return JwtUserDetails.create(person);
	}

}
