package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gmail {
    private static final Logger logger = Logger.getLogger(Gmail.class.getName());
    public void sendEmail(String to,String topicName,String msg ) {
        String from = "samaabusair12@gmail.com";
     //   String to = "s12029704@stu.najah.edu";

        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("samaabusair12@gmail.com", "ijhz fmux ssdj tana");
                }
            });
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, false));
            message.setSubject(topicName);
            message.setText(msg);
            Transport.send(message);
        } catch (MessagingException m) {
            logger.log(Level.SEVERE, "An error occurred", m);
        }


    }
}
