package com.LabSystem.Lab.email;

import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    private final JavaMailSender mailSender;

    public SendEmailService(JavaMailSender mailSender) {this.mailSender=mailSender;}

    @EventListener
    public void handleSendEmailEvent(SendEmailEvent event){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("aselagayan1010@gmail.com");
        message.setTo(event.getToEmail());
        message.setText(event.getBody());
        message.setText(event.getSubject());

        mailSender.send(message);

        System.out.println("mail Sent Sucessfully" +event.getToEmail());
    }

}
