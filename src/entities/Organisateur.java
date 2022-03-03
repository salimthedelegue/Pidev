/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author nours
 */
public class Organisateur {
    int id_organisateur;
    String nom,type,email;
    int numTel_organisateur;

    public Organisateur(String nom, String type, String email, int numTel_organisateur) {
        this.nom = nom;
        this.type = type;
        this.email = email;
        this.numTel_organisateur = numTel_organisateur;
    }

    public Organisateur() {
           }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel_organisateur() {
        return numTel_organisateur;
    }

    public void setNumTel_organisateur(int numTel_organisateur) {
        this.numTel_organisateur = numTel_organisateur;
    }

    @Override
    public String toString() {
        return "Organisateur{" + "id_organisateur=" + id_organisateur + ", nom=" + nom + ", type=" + type + ", email=" + email + ", numTel_organisateur=" + numTel_organisateur + '}';
    }
    
}
