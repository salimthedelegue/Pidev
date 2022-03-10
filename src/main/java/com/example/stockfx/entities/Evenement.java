/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.entities;

/**
 *
 * @author nours
 */
public class Evenement {
    private String nom;
    private String dateD;
    private String dateF;
    private float prix;
    private String image;
    private String description;
    private String emplacement;
    private int id_organisateur;
    private int nbr_participant,nbr_max_participant;
    private int ref;

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public Evenement(String nom, String dateD, String dateF, float prix, String image, String description, String emplacement, int id_organisateur,int nbr_max_participant) {
        this.nom = nom;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.emplacement = emplacement;
        this.id_organisateur = id_organisateur;
        this.nbr_max_participant=nbr_max_participant; 
        
    }

    public Evenement() {
       }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateD() {
        return dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public int getNbr_max_participant() {
        return nbr_max_participant;
    }

    public void setNbr_max_participant(int nbr_max_participant) {
        this.nbr_max_participant = nbr_max_participant;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nom=" + nom + ", dateD=" + dateD + ", dateF=" + dateF + ", prix=" + prix + ", image=" + image + ", description=" + description + ", emplacement=" + emplacement + ", id_organisateur=" + id_organisateur + ", nbr_participant=" + nbr_participant + ", nbr_max_participant=" + nbr_max_participant + '}';
    }

    
    
    
    
    
}
