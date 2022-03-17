/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.activate.utilities;

import com.unicauca.activate.model.ClaimManager;

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
