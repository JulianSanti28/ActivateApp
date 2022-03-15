/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.service;

/**
 *
 * @author 57322
 */
public interface IEmailServiceSupport {

    public void sendEmailSupport(String to, String subject, String body);

    public void init(String to, String subject, String body);

}
