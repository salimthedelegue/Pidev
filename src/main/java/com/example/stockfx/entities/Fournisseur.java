package com.example.stockfx.entities;

public class Fournisseur {

    private int idFournisseur;
    private String nom_fournisseur;
    private String num_telephone;
    private String num_fixe;
    private String email;
    private String matricule;
    private String type_véhicule;
    private String photo;

    public Fournisseur(){

    }
    public Fournisseur(String nom_fournisseur ,String num_telephone, String num_fixe, String email, String matricule, String photo) {
        this.nom_fournisseur = nom_fournisseur;
        this.num_telephone = num_telephone;
        this.num_fixe = num_fixe;
        this.email = email;
        this.matricule = matricule;
        this.photo = photo;
    }
    public Fournisseur(int id_fournisseur,String nom_fournisseur, String num_telephone, String num_fixe, String email, String matricule, String photo) {
        this.idFournisseur=id_fournisseur;
        this.nom_fournisseur = nom_fournisseur;
        this.num_telephone = num_telephone;
        this.num_fixe = num_fixe;
        this.email = email;
        this.matricule = matricule;
        this.type_véhicule = type_véhicule;
        this.photo = photo;
    }



    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(String num_telephone) {
        this.num_telephone = num_telephone;
    }

    public String getNum_fixe() {
        return num_fixe;
    }

    public void setNum_fixe(String num_fixe) {
        this.num_fixe = num_fixe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType_véhicule() {
        return type_véhicule;
    }

    public void setType_véhicule(String type_véhicule) {
        this.type_véhicule = type_véhicule;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    public int getId_fournisseur() {
        return idFournisseur;
    }

    public void setId_fournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    @Override
    public String toString() {
        return "Fournisseur{" +
                "idFournisseur=" + idFournisseur +
                ", nom_fournisseur='" + nom_fournisseur + '\'' +
                ", num_telephone='" + num_telephone + '\'' +
                ", num_fixe='" + num_fixe + '\'' +
                ", email='" + email + '\'' +
                ", matricule='" + matricule + '\'' +
                ", type_véhicule='" + type_véhicule + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }



}
