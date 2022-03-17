/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.controller;

import com.unicauca.activate.mapper.Mapper;
import com.unicauca.activate.utilities.Utilities;
import com.unicauca.activate.model.Claim;
import com.unicauca.activate.model.ClaimDTO;
import com.unicauca.activate.model.ClaimManager;
import com.unicauca.activate.model.User;
import com.unicauca.activate.service.IClaimService;
import com.unicauca.activate.service.IEmailServiceClient;
import com.unicauca.activate.service.IUserService;
import com.unicauca.activate.utilities.JWTUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.activate.service.IEmailServiceSupport;
import com.unicauca.activate.utilities.EmailTemplateUtil;

/**
 *
 * @author Julián S Martinez T
 */
@RestController
@RequestMapping("/activate/claim")
public class ClaimController {

    @Autowired
    private IClaimService claimService;

    @Autowired
    private IEmailServiceSupport emailServiceSupport; //Inyección de dependencias

    @Autowired
    private IEmailServiceClient emailServiceClient; //Inyección de dependencias

    @Autowired
    private IUserService userService;

    @Autowired
    private JWTUtilities jwUtil;

    //Crear Usuario
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestHeader(value = "Authorization") String token, @RequestBody ClaimDTO claimDto) {
        /*Id del Usuario*/
        Long userId = Long.parseLong(jwUtil.getKey(token));
        /*Obtener el Usuario*/
        User user = userService.findById(userId).get();
        /**
         * Se ontiene el manager y se inician valores
         */
        ClaimManager manager = Utilities.getClaimManager();
        manager.createAthentionFlow();
        /**
         * Convertimos al objeto deseado
         */
        Mapper mapper = Mapper.getMapper();
        Claim claim = mapper.toClaim(claimDto);

        /**
         * Se puede atender la solicitud? Entonces notificamos vía email a los
         * usuarios y a nuestros colaboradores(En un hilo distinto para que
         * nuestra API REST responda y no espere el envío de esto)
         */
        if (manager.getLevelOne().attend(claim)) {
            //Iniciar los valores del sevricio de email de soporte
            emailServiceSupport.init(claim.getEmail(), "Claim Request Attention!", "Hello! You must answer this request. More details of the user's request are added below:"
                    + "\n" + "Claim Tittle: " + claim.getTitle() + "\n" + "Claim Description: " + claim.getDescription() + "\n" + "Claim Register Date:" + claim.getDate() + "\n" + "Atention Type:" + claim.getType().toString() + "\n" + "Thank you so much!");
            Thread emailSupport = new Thread((Runnable) emailServiceSupport);
            emailSupport.start();

            //Iniciar los valores del sevricio de email de Cliente
            emailServiceClient.init(user.getEmail(), EmailTemplateUtil.CLAIM_SUCCESSFULLY_CREATED_SUBJECT, "Hello " + user.getName() + ", " + "your claim request will be answered very soon, our team is working for you. Details of your request:"
                    + "\n" + "Claim Tittle: " + claim.getTitle() + "\n" + "Claim Description: " + claim.getDescription() + "\n" + "Claim Register Date:" + claim.getDate() + "\n" + "Atention Type:" + claim.getType().toString() + "\n" + "Thank you so much!");
            Thread emailClient = new Thread((Runnable) emailServiceClient);
            emailClient.start();

        }
        Claim save = claimService.save(claim);
        return ResponseEntity.ok().body(save);
    }

}
