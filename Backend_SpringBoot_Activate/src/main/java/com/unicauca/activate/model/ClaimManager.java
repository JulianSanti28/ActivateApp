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
public class ClaimManager {
    
    private ClaimLevelOne levelOne;
    private ClaimLevelTwo levelTwo;
    private ClaimLevelThree levelThree;
    private ClaimLevelFour levelFour;
    
    public void createAthentionFlow() {
        levelOne = new ClaimLevelOne("Julián Santiago", "Martinez Trullo", "juliansmartinez@unicauca.edu.co");
        levelTwo = new ClaimLevelTwo("Paula Andrea", "Peña Constain", "ppena@unicauca.edu.co");
        levelThree = new ClaimLevelThree("Miguel Andres", "Mosquera Monje", "miguelmonje@unicauca.edu.co");
        levelFour = new ClaimLevelFour("Sebastian", "Arenas Rodriguez", "juliansmartinez@unicauca.edu.co");
        // Crea los enlaces
        levelOne.setNextHandler(levelTwo);
        levelTwo.setNextHandler(levelThree);
        levelThree.setNextHandler(levelFour);
    }

    /**
     * Getters and Setters
     *
     * @return
     */
    public ClaimLevelOne getLevelOne() {
        return levelOne;
    }
    
    public void setLevelOne(ClaimLevelOne levelOne) {
        this.levelOne = levelOne;
    }
    
    public ClaimLevelTwo getLevelTwo() {
        return levelTwo;
    }
    
    public void setLevelTwo(ClaimLevelTwo levelTwo) {
        this.levelTwo = levelTwo;
    }
    
    public ClaimLevelThree getLevelThree() {
        return levelThree;
    }
    
    public void setLevelThree(ClaimLevelThree levelThree) {
        this.levelThree = levelThree;
    }
    
}
