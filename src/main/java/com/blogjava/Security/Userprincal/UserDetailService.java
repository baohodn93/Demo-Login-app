package com.blogjava.Security.Userprincal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogjava.model.User;
import com.blogjava.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username).orElseThrow(()->
			new UsernameNotFoundException("User not found -> username or password" + username));
		
		return UserPrinciple.build(user);
	}

}
