/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.utilities;

import com.unicauca.activate.model.ClaimManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author 57322
 */
public class Utilities {



    /**
     * Crea los valores iniciales para la atención de reclamaciones
     *
     * @return
     */
    public static ClaimManager getClaimManager() {
        return new ClaimManager();
    }

}
