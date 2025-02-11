/** Clasa pentru GymDTO
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.models;

import java.util.List;

public class GymDTO {

    private String numeSala;
    private List<String> descriere;
    private String tipAb;

    public String getNumeSala() {
        return numeSala;
    }

    public void setNumeSala(String numeSala) {
        this.numeSala = numeSala;
    }

    public List<String> getDescriere() {
        return descriere;
    }

    public void setDescriere(List<String> descriere) {
        this.descriere = descriere;
    }

    public String getTipAb() {
        return tipAb;
    }

    public void setTipAb(String tipAb) {
        this.tipAb = tipAb;
    }
}
