package com.unibrain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.unibrain.controller.BaseController;

@Component
public class EmailService extends BaseController implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	protected ServletContext servletContext;

	private String toEmail;
	private String mailSubject;
	private String mailBody;
	private String mailDisclaimer;
	private String name;

	public EmailService(String toEmail, String mailSubject, String mailBody, String mailDisclaimer, String name) {
		this.toEmail = toEmail;
		this.mailSubject = mailSubject;
		this.mailBody = mailBody;
		this.mailDisclaimer = mailDisclaimer;
		this.name = name;
	}

	public EmailService() {

	}

	public void run() {
		sendEmail(toEmail, mailSubject, mailBody, mailDisclaimer, name);
	}

	public String sendEmail(String toEmail, String subject, String mailBody, String mailDisclaimer, String name) {

		StringBuffer jsonString = new StringBuffer();
		try {
			// String api_url =
			// "https://teleduce.corefactors.in/send-email-json-otom/f86a7b6e-38bd-4fe7-ba72-b980d9019624/1009/";
			String api_url = "https://teleduce.corefactors.in/send-email-json-otom/f86a7b6e-38bd-4fe7-ba72-b980d9019624/1007/";
			String payloadstring = "";
			JSONArray recepientsArray = new JSONArray();
			JSONObject recepient1 = new JSONObject();
			recepient1.put("email_id", toEmail);
			recepient1.put("name", name);
			recepientsArray.put(recepient1);
			JSONObject payloadbody = new JSONObject();
			payloadbody.put("html_content", "<html><head><title>" + "</title></head><body><p>" + mailBody
					+ "</p><br><p>" + mailDisclaimer + "</p></body></html>");
			payloadbody.put("subject", subject);
			payloadbody.put("from_name", "eWallet");
			payloadbody.put("from_mail", "admin@euniwizarde.com");
			payloadbody.put("reply_to", "admin@euniwizarde.com");
			payloadbody.put("to_recipients", recepientsArray);
			JSONObject payload = new JSONObject();
			payload.put("message", payloadbody);
			JSONObject payloadContainer = new JSONObject();
			payloadContainer.put("mail_datas", payload);
			payloadstring = payloadContainer.toString();
			URL url = new URL(api_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(payloadstring);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}

			br.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		logger.info(jsonString.toString());
		return jsonString.toString();

	}

	public void sendEmailWithFile(String toEmail, String subject, String mailBody, String fileType, String fileName,
			byte[] attachment) {
		// MimeMessage message = mailSender.createMimeMessage();
		// // integrate time check code once
		// try {
		// MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);
		// mimeMessage.setTo(toEmail);
		// mimeMessage.setFrom(mailSender.getUsername());
		// mimeMessage.setSubject(subject);
		// mimeMessage.setSentDate(new Date());
		// mimeMessage.setText(mailBody);
		// mimeMessage.addAttachment("a.jpg", new
		// ClassPathResource("memorynotfound-logo.jpg"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new RuntimeException(e.getMessage());
		// }
		//
		// mailSender.send(message);
	}

	public void sendEmailWithAttachments(String toMail, String subject, String message, String attachFiles)
			throws AddressException, MessagingException, IOException {

		logger.info("inside sendEmailWithAttachments=============");
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailSender.getUsername(), mailSender.getPassword());
			}
		};

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.smtp.host", mailSender.getHost());
		properties.put("mail.smtp.port", mailSender.getPort());
		properties.put("mail.user", mailSender.getUsername());
		properties.put("mail.password", mailSender.getPassword());

		Session session = Session.getInstance(properties, auth);
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailSender.getUsername()));
		InternetAddress[] toAddresses = { new InternetAddress(toMail) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile(attachFiles);
		multipart.addBodyPart(attachPart);

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);
	}

}
