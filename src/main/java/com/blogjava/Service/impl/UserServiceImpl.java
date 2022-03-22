package com.blogjava.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogjava.Service.IUserService;
import com.blogjava.model.User;
import com.blogjava.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Override
	public Optional<User> findByUserName(String name) {
		return userRepository.findByUserName(name);
	}

	@Override
	public Boolean existsByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUserName(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public User save(User user) {

		return userRepository.save(user);
	}

}
