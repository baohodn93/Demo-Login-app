package com.blogjava.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.New;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogjava.Security.Jwt.JwtProvider;
import com.blogjava.Security.Userprincal.UserPrinciple;
import com.blogjava.Service.impl.RoleServiceImpl;
import com.blogjava.Service.impl.UserServiceImpl;
import com.blogjava.dto.request.SignInForm;
import com.blogjava.dto.request.SignUpForm;
import com.blogjava.dto.response.JwtResponse;
import com.blogjava.dto.response.ResponseMessage;
import com.blogjava.model.Role;
import com.blogjava.model.RoleName;
import com.blogjava.model.User;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RoleServiceImpl roleService;

	@Autowired
	private PasswordEncoder passWordEncode;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/signup")
	public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
		if (userService.existsByUserName(signUpForm.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again!"), HttpStatus.OK);
		}

		if (userService.existsByEmail(signUpForm.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("The email existed! Please try again!"), HttpStatus.OK);
		}
		User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),
				passWordEncode.encode(signUpForm.getPassword()));
		Set<String> strRoles = signUpForm.getRoles();
		Set<Role> roles = new HashSet<>();
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleService.findByName(RoleName.ADMIN)
						.orElseThrow(() -> new RuntimeException("Role not found"));
				roles.add(adminRole);
				break;
			case "pm":
				Role pmRole = roleService.findByName(RoleName.PM)
						.orElseThrow(() -> new RuntimeException("Role not found"));
				roles.add(pmRole);
				break;
			default:
				Role userRole = roleService.findByName(RoleName.USER)
						.orElseThrow(() -> new RuntimeException("Role not found"));
				roles.add(userRole);
			}
		});
		user.setRoles(roles);
		userService.save(user);
		return new ResponseEntity<>(new ResponseMessage("Create user success!"), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.createToken(authentication);
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(userPrinciple.getId(),
				token, 
				userPrinciple.getName(), 
				userPrinciple.getAuthorities()));
	}
}
