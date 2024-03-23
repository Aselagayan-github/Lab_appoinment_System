package com.LabSystem.Lab.email;

import org.springframework.context.ApplicationEvent;

public class SendEmailEvent extends ApplicationEvent {
    private final String toEmail;

    private final String subject;

    private final String body;


    public SendEmailEvent(Object source, String toEmail, String body, String subject) {
        super(source);
        this.toEmail = toEmail;
        this.body = body;
        this.subject= subject;
    }



    public String getBody() {
        return body;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getSubject() {
        return subject;
    }
}
