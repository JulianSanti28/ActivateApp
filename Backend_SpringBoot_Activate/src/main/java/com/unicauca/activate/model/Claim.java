
package com.unicauca.activate.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 57322
 */
@Entity
@Table(name = "pqrs")
public class Claim {

    /**
     * Atributos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", length = 50)
    private String title;
    @Column(name = "descripcion", length = 250)
    private String description;
    @Column(name = "tipo")
    private ClaimType type;
    @Column(name = "fecha_registro")
    private LocalDate date;
    @Column(name = "estado")
    private boolean attended;
    @Column(name = "atendida_por")
    private String email;

    /**
     * Constructor con parámetros
     *
     * @param title
     * @param description
     * @param type
     */
    public Claim(String title, String description) {
        this.title = title;
        this.description = description;
        this.attended = false;
        this.date = LocalDate.now();
    }
    /**
     * Constructor
     */
    public Claim() {
    }

    /**
     * Getters and Setters
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimType getType() {
        return type;
    }

    public void setType(ClaimType type) {
        this.type = type;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
