package com.blogjava.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogjava.Service.IEmailService;
import com.blogjava.Service.Utility;
import com.blogjava.Service.impl.UserServiceImpl;
import com.blogjava.dto.request.ResetPassword;
import com.blogjava.dto.response.ResponseMessage;
import com.blogjava.model.User;

@RestController
@RequestMapping
@CrossOrigin("*")
public class PasswordController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private PasswordEncoder passWordEncode;

	// Process form submission from forgotPassword page
	@PostMapping("/forgot-password")
	public ResponseEntity<?> resetPassword(HttpServletRequest request, @Valid @RequestBody ResetPassword resetPassword) {

		Optional<User> optional = userService.findByEmail(resetPassword.getEmail());
		if (!optional.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("Email does not exist"), HttpStatus.OK);
		}
		try {
			User user = optional.get();
			user.setResetToken(UUID.randomUUID().toString());
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":4200/reset-password";

	        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + user.getResetToken();
	        emailService.sendEmail(user.getEmail(), resetPasswordLink);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>(new ResponseMessage("Email existed"), HttpStatus.OK);

		// Email message
//		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//		passwordResetEmail.setFrom("support@demo.com");
//		passwordResetEmail.setTo(user.getEmail());
//		passwordResetEmail.setSubject("Password Reset Request");
//		passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
//				+ "/reset?token=" + user.getResetToken());
//
//		emailService.sendEmail(passwordResetEmail);
	}
}
