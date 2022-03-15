/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian S Martinez
 */
@Service
public class EmailServiceClient implements IEmailServiceClient, Runnable {

    private String to;
    private String subject;
    private String body;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void run() {
        sendEmailClient(this.to, this.subject, this.body);
    }

    @Override
    public void sendEmailClient(String to, String subject, String body) {

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, true);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailServiceSupport.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setFrom("support@activate.com");
            } catch (MessagingException ex) {
                Logger.getLogger(EmailServiceSupport.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setTo(to);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailServiceSupport.class.getName()).log(Level.SEVERE, null, ex);
            }
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource file = new FileSystemResource(new File("./files/imagesEmail/email.png"));
            helper.addInline("activate", file);
            emailSender.send(mimeMessage);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceSupport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void init(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;

    }
}
