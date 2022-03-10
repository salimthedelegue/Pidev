/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nours
 */
public class Magazine {
    private String titre_magazine,genre_magazine,image_magazine,description_magazine;

    public void setTitre_magazine(String titre_magazine) {
        this.titre_magazine = titre_magazine;
    }

    public void setGenre_magazine(String genre_magazine) {
        this.genre_magazine = genre_magazine;
    }

    public void setImage_magazine(String image_magazine) {
        this.image_magazine = image_magazine;
    }

    public void setDescription_magazine(String description_magazine) {
        this.description_magazine = description_magazine;
    }
    int ref;

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getRef() {
        return ref;
    }
    

    public String getTitre_magazine1() {
        return titre_magazine;
    }

    public String getGenre_magazine1() {
        return genre_magazine;
    }

    public String getImage_magazine1() {
        return image_magazine;
    }

    public String getDescription_magazine1() {
        return description_magazine;
    }

    public Magazine(String titre_magazine, String genre_magazine, String image_magazine, String description_magazine) {
        this.titre_magazine = titre_magazine;
        this.genre_magazine = genre_magazine;
        this.image_magazine = image_magazine;
        this.description_magazine = description_magazine;
    }

    

    public Magazine() {
           }

    public SimpleStringProperty getTitre_magazine() {
        return new SimpleStringProperty(titre_magazine);
    }
 
   /* public void setTitre_magazine(String titre_magazine) {
       this.titre_magazine = new SimpleStringProperty(titre_magazine);
    }*/

    public SimpleStringProperty getGenre_magazine() {
        return new SimpleStringProperty(genre_magazine);
    }

   /* public void setGenre_magazine(String genre_magazine) {
         this.genre_magazine = new SimpleStringProperty(genre_magazine);
    }*/

    public SimpleStringProperty getImage_magazine() {
        return new SimpleStringProperty(image_magazine);
    }

   /* public void setImage_magazine(String image_magazine) {
         this.image_magazine = new SimpleStringProperty(image_magazine);
    }*/

    public SimpleStringProperty getDescription_magazine() {
        return new SimpleStringProperty(description_magazine);
    }

   /* public void setDescription_magazine(String description_magazine) {
         this.description_magazine = new SimpleStringProperty(description_magazine);
    }*/

    @Override
    public String toString() {
        return "ref: "+ref+" ,titre_magazine=" + titre_magazine + ", genre_magazine=" + genre_magazine + ", image_magazine=" + image_magazine + ", description_magazine=" + description_magazine + '}';
    }
    
    
}
