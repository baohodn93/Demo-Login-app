package com.blogjava.repository;

import java.util.Optional;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogjava.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String name); //Tim kiem co ton tai trong DB khong
	Boolean existsByUserName(String username);//check username da co trong db chua, khi tao data
	Boolean existsByEmail(String email);//check email da co trong db chua
}
