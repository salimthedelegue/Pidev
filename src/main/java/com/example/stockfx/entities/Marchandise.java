
package com.example.stockfx.entities;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

public class Marchandise {

    private int id_marchandise;
    private String categorie_marchandise;
    private String nom_marchandise;
    private double quantite;
    private String date_arrive;
    private float prix_unitaire_marchandise;
    private float prix_total_marchandise;

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    private int id_fournisseur;

    Blob blob;
    InputStream in;
    InputStream input;
    OutputStream output;

    public Marchandise() {
    }

    public Marchandise(int id_marchandise) {
        this.id_marchandise = id_marchandise;
    }

    /*
        public Marchandise(int id_marchandise,String categorie_marchandise, String nom_marchandise,String date_arrive,double quantite,float prix_unitaire_marchandise,float prix_total_marchandise) {
            this.id_marchandise = id_marchandise;
            this.categorie_marchandise = categorie_marchandise;
            this.nom_marchandise = nom_marchandise;
            this.quantite = quantite;
            this.date_arrive = date_arrive;
            this.prix_unitaire_marchandise = prix_unitaire_marchandise;
            this.prix_total_marchandise = prix_total_marchandise;
            this.id_fournisseur = id_fournisseur;
        }
        */
    public Marchandise(String categorie_marchandise, double quantite){
        this.categorie_marchandise=categorie_marchandise;
        this.quantite=quantite;

    }
    public Marchandise(String categorie_marchandise, String nom_marchandise, String date_arrive, double quantite, float prix_unitaire_marchandise, float prix_total_marchandise, int id_fournisseur) {
        this.categorie_marchandise = categorie_marchandise;
        this.nom_marchandise = nom_marchandise;
        this.quantite = quantite;
        this.date_arrive = date_arrive;
        this.prix_unitaire_marchandise = prix_unitaire_marchandise;
        this.prix_total_marchandise = prix_total_marchandise;
        this.id_fournisseur = id_fournisseur;
    }
    public Marchandise(String categorie_marchandise, String nom_marchandise, String date_arrive, double quantite, float prix_unitaire_marchandise, float prix_total_marchandise) {
        this.categorie_marchandise = categorie_marchandise;
        this.nom_marchandise = nom_marchandise;
        this.quantite = quantite;
        this.date_arrive = date_arrive;
        this.prix_unitaire_marchandise = prix_unitaire_marchandise;
        this.prix_total_marchandise = prix_total_marchandise;
        this.id_fournisseur = id_fournisseur;
    }
    public Marchandise(int id_marchandise,String categorie_marchandise, String nom_marchandise, String date_arrive, double quantite, float prix_unitaire_marchandise, float prix_total_marchandise, int id_fournisseur) {
        this.id_marchandise=id_marchandise;
        this.categorie_marchandise = categorie_marchandise;
        this.nom_marchandise = nom_marchandise;
        this.quantite = quantite;
        this.date_arrive = date_arrive;
        this.prix_unitaire_marchandise = prix_unitaire_marchandise;
        this.prix_total_marchandise = prix_total_marchandise;
        this.id_fournisseur = id_fournisseur;
    }

    public int getId_marchandise() {
        return id_marchandise;
    }

    public void setId_marchandise(int id_marchandise) {
        this.id_marchandise = id_marchandise;
    }

    public String getCategorie_marchandise() {
        return categorie_marchandise;
    }

    public void setCategorie_marchandise(String categorie_marchandise) {
        this.categorie_marchandise = categorie_marchandise;
    }

    public String getNom_marchandise() {
        return nom_marchandise;
    }

    public void setNom_marchandise(String nom_marchandise) {
        this.nom_marchandise = nom_marchandise;
    }
    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(String date_arrive) {
        this.date_arrive = date_arrive;
    }

    public float getPrix_unitaire_marchandise() {
        return prix_unitaire_marchandise;
    }

    public void setPrix_unitaire_marchandise(float prix_unitaire_marchandise) {
        this.prix_unitaire_marchandise = prix_unitaire_marchandise;
    }

    public float getPrix_total_marchandise() {
        return prix_total_marchandise;
    }

    public void setPrix_total_marchandise(float prix_total_marchandise) {
        this.prix_total_marchandise = prix_total_marchandise;
    }



    @Override
    public String toString() {
        return "Marchandise{" +
                "id_marchandise=" + id_marchandise +
                ", categorie_marchandise='" + categorie_marchandise + '\'' +
                ", nom_marchandise='" + nom_marchandise + '\'' +
                ", quantite=" + quantite +
                ", date_arrive='" + date_arrive + '\'' +
                ", prix_unitaire_marchandise=" + prix_unitaire_marchandise +
                ", prix_total_marchandise=" + prix_total_marchandise +
                ", id_fournisseur='" + id_fournisseur + '\'' +
                '}';
    }

}

