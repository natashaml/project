package com.itran.booksjournal.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Mail
{
	static String username = "natasha.ml.lev1993@gmail.com";

	static String password = "starlight-1993";

	private MailSender mailSender;

	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage)
	{
		this.templateMessage = templateMessage;
	}

	public void send(String subject, String text, String email)
	{
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(text);
		try
		{
			this.mailSender.send(msg);
		} catch (MailException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
