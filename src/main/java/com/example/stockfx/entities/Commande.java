/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.entities;

import java.sql.Date;

/**
 *
 * @author ahmed amine
 */
public class Commande {
    
    public int id_commande;
    public int type_commande;
    public Date date_commande;
    public String adresse_livraison;
    public float prix_totale;
    public int id_client;

    public Commande(int id_commande, int type_commande, Date date_commande, String adresse_livraison, float prix_totale) {
        this.id_commande = id_commande;
        this.type_commande = type_commande;
        this.date_commande = date_commande;
        this.adresse_livraison = adresse_livraison;
        this.prix_totale = prix_totale;
    }

    public Commande(int type_commande, Date date_commande, String adresse_livraison, float prix_totale, int id_client) {
        this.type_commande = type_commande;
        this.date_commande = date_commande;
        this.adresse_livraison = adresse_livraison;
        this.prix_totale = prix_totale;
        this.id_client = id_client;
    }

    public Commande(int id_commande, int type_commande, Date date_commande, String adresse_livraison, float prix_totale, int id_client) {
        this.id_commande = id_commande;
        this.type_commande = type_commande;
        this.date_commande = date_commande;
        this.adresse_livraison = adresse_livraison;
        this.prix_totale = prix_totale;
        this.id_client = id_client;
    }

    

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public float getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(float prix_totale) {
        this.prix_totale = prix_totale;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", type_commande=" + type_commande + ", date_commande=" + date_commande + ", adresse_livraison=" + adresse_livraison + ", prix_totale=" + prix_totale + '}';
    }


    
    
    
}
