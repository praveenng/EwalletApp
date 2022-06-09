package com.unibrain.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfig {

	@Autowired
	protected Environment env;

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasenames("classpath:messages", "classpath:ewallet_logs", "classpath:email_message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000/");
			}
		};
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setPort(Integer.valueOf(env.getProperty("unibrain.mail.port")));
		mailSender.setHost(env.getProperty("unibrain.mail.host"));
		mailSender.setProtocol(env.getProperty("unibrain.mail.protocol"));
		mailSender.setUsername(env.getProperty("unibrain.mail.sender.username"));
		mailSender.setPassword(env.getProperty("unibrain.mail.sender.password"));

		final Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", env.getProperty("unibrain.mail.smtp.auth"));
		mailProperties.setProperty("mail.smtp.starttls.enable", env.getProperty("unibrain.mail.smtp.starttls.enable"));
		mailProperties.setProperty("mail.smtp.debug", env.getProperty("unibrain.mail.smtp.debug"));
		mailProperties.setProperty("mail.defaultEncoding", env.getProperty("unibrain.mail.defaultEncoding"));
		mailProperties.setProperty("mail.smtp.socketFactory.port ", env.getProperty("unibrain.mail.port"));
		mailProperties.setProperty("mail.smtp.socketFactory.class",
				env.getProperty("unibrain.mail.smtp.socketFactory.class"));
		mailProperties.setProperty("mail.smtp.localhost", env.getProperty("unibrain.mail.host"));
		mailProperties.setProperty("mail.debug", "false");

		mailSender.setJavaMailProperties(mailProperties);

		return mailSender;
	}

}
