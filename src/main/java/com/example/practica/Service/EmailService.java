package com.example.practica.Service;

import com.example.practica.DTO.MailBody;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

   public void sendSimpleMessage(MailBody mailBody) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mailBody.to());
    message.setFrom("marian1112333@gmail.com");
    message.setSubject(mailBody.subject());
    message.setText(mailBody.text());
    mailSender.send(message);
   }
}