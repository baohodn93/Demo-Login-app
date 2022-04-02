package com.blogjava.Service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface IEmailService {

	public void sendEmail(String email, String link) throws MessagingException, UnsupportedEncodingException;

}
