package com.blogjava.Service;

import java.util.List;
import java.util.Optional;

import com.blogjava.model.User;

public interface IUserService {
	Optional<User> findByUserName(String name); //Tim kiem co ton tai trong DB khong

	Boolean existsByUserName(String username);//check username da co trong db chua, khi tao data
	Boolean existsByEmail(String email);//check email da co trong db chua
	User save(User user);
	
	List<User> findAll();
	
    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String resetToken);
}
