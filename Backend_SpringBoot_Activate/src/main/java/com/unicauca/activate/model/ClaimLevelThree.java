/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.model;

/**
 *
 * @author 57322
 */
public class ClaimLevelThree extends ClaimHandler {

    public ClaimLevelThree(String nombre, String apellidos, String email) {
        super(nombre, apellidos, email);
    }

    @Override
    public boolean attend(Claim claim) {
        if (claim.getType().equals(ClaimType.ALTA)) {
            claim.setAttended(true);
            claim.setEmail(getEmail());
            return true;
        } else {
            return getNextHandler().attend(claim);
        }
    }

}
