/** Clasa pentru Gyms
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.models;

import jakarta.persistence.*;

@Entity
@Table(name="gyms")
public class Gyms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long idSala;

    @Column(name = "nume_sala", nullable = false)
    private String numeSala;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "tip_ab", nullable = false)
    private String tipAb;

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public String getNumeSala() {
        return numeSala;
    }

    public void setNumeSala(String numeSala) {
        this.numeSala = numeSala;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getTipAb() {
        return tipAb;
    }

    public void setTipAb(String tipAb) {
        this.tipAb = tipAb;
    }
}
