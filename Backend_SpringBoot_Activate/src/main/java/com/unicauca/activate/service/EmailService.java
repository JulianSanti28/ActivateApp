/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

import java.io.File;
import java.util.List;
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
 * @author 57322
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmailSupport(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, true);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setFrom("support@activate.com");
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setTo(to);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource file = new FileSystemResource(new File("./files/imagesEmail/email.png"));
            helper.addInline("activate", file);
            emailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendEmailClient(String to, String subject, String body) {
        
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, true);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setFrom("support@activate.com");
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                helper.setTo(to);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
            }
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource file = new FileSystemResource(new File("./files/imagesEmail/email.png"));
            helper.addInline("activate", file);
            emailSender.send(mimeMessage);
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
