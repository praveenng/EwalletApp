package com.unibrain.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class EmailAndMessageService {

	@Autowired
	private MessageSource messageSource;

	public void buildEmailContent(String toEmail, int emailvalue, HttpServletRequest request, String... values)
			throws Exception {
		String mailSubject = null;
		String mailBody = null;
		String mailDisclaimer = messageSource.getMessage("email.disclaimer", null, null);
		EmailService es = null;
		Thread t = null;

		switch (emailvalue) {
		case 1:
			mailSubject = messageSource.getMessage("email.ewalletUser.otpLogin.subject", null, null);
			mailBody = messageSource.getMessage("email.ewalletUser.otpLogin.body",
					new String[] { values[0], values[1], values[2] }, null);
			es = new EmailService(toEmail, mailSubject, mailBody, mailDisclaimer, values[0]);
			t = new Thread(es);
			t.start();
			break;
		}
	}
}
