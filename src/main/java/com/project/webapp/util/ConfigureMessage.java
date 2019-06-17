package com.project.webapp.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

public class ConfigureMessage {


    public static Message message (String addressTo, String addressCc, String addressBcc, String subject) throws MessagingException {

        try{
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.EMAIL, Constants.PASSWORD);
                }
            };

            Message message = new MimeMessage(Session.getInstance(props, auth));
            message.setFrom(new InternetAddress(Constants.EMAIL));
            InternetAddress[] toAdressArray = InternetAddress.parse(addressTo);
            InternetAddress[] ccAdressArray = InternetAddress.parse(addressCc);
            InternetAddress[] bccAdressArray = InternetAddress.parse(addressBcc);

            message.addRecipients(Message.RecipientType.TO, toAdressArray);
            message.addRecipients(Message.RecipientType.CC, ccAdressArray);
            message.addRecipients(Message.RecipientType.BCC, bccAdressArray);
            message.setSubject(subject);
            return message;
        }catch (MessagingException e) {
            throw new WebShopException(HttpStatus.INTERNAL_SERVER_ERROR,"Email sending failed!");
        }

    }

}
