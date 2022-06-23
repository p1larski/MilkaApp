package com.example.milkaapp.tools.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailSende {

        @Autowired
        private JavaMailSender emailSender;

        @Async
        public void sendSimpleMessage(String to, String token) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("email");
            message.setTo(to);
            message.setSubject("Potwierdzenie adresu email");
            message.setText(token);
            emailSender.send(message);
        }

}
