package com.altimetrik.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendAcknowelededEmailToSender {

	public static void send() {

		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("manvithaprema@gmail.com", "Alti@123");
			}
		});

		String toMail = RecieveEmailWithAttachment.toMail != null
				? RecieveEmailWithAttachment.toMail.substring(RecieveEmailWithAttachment.toMail.indexOf('<') + 1,
						RecieveEmailWithAttachment.toMail.indexOf('>'))
				: "";

		try {
			if (RecieveEmailWithAttachment.toMail != null) {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
				message.setSubject("AKNOWLEDGEMENT");
				message.setText(" Hii, INVOICE RECEIVED sucessfully and is Approved");
				// send message
				Transport.send(message);
				System.out.println("message sent successfully");
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
