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
public class Article {
    int ref_article,ref_magazine;
    float rate;
    String titre_article,contenu_article,auteur_article,genre_article,date_article;

    public Article( String titre_article, String contenu_article, String auteur_article, String genre_article, String date_article,int ref_magazine) {
        this.ref_magazine = ref_magazine;
        this.rate = rate;
        this.titre_article = titre_article;
        this.contenu_article = contenu_article;
        this.auteur_article = auteur_article;
        this.genre_article = genre_article;
        this.date_article = date_article;
    }

    

    public Article() {
        }

  
    public int getRef_article() {
        return ref_article;
    }

    public void setRef_article(int ref_article) {
        this.ref_article = ref_article;
    }

    public int getRef_magazine() {
        return ref_magazine;
    }

    public void setRef_magazine(int ref_magazine) {
        this.ref_magazine = ref_magazine;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTitre_article() {
        return titre_article;
    }

    public void setTitre_article(String titre_article) {
        this.titre_article = titre_article;
    }

    public String getContenu_article() {
        return contenu_article;
    }

    public void setContenu_article(String contenu_article) {
        this.contenu_article = contenu_article;
    }

    public String getAuteur_article() {
        return auteur_article;
    }

    public void setAuteur_article(String auteur_article) {
        this.auteur_article = auteur_article;
    }

    public String getGenre_article() {
        return genre_article;
    }

    public void setGenre_article(String genre_article) {
        this.genre_article = genre_article;
    }

    public String getDate_article() {
        return date_article;
    }

    public void setDate_article(String date_article) {
        this.date_article = date_article;
    }

    @Override
    public String toString() {
        return "Article{" + "ref_article=" + ref_article + ", ref_magazine=" + ref_magazine + ", rate=" + rate + ", titre_article=" + titre_article + ", contenu_article=" + contenu_article + ", auteur_article=" + auteur_article + ", genre_article=" + genre_article + ", date_article=" + date_article + '}';
    }
    
}
