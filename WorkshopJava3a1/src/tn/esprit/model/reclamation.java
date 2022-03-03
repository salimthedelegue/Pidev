package tn.esprit.model;

import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author salim
 */
public class reclamation {
  private int id;
  private LocalDateTime date;  
  private String description;  
  private int id_commande;
  private int id_service;
  
  public reclamation(){
        }
  
  public reclamation(int id, LocalDateTime date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
        //this.id_commande=id_commande;
        //this.id_service=id_service;
    }
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getIdcommande() {
        return id;
    }

    public void setIdCommande(int id) {
        this.id = id;
    }
    public int getIdservice() {
        return id;
    }

    public void setIdservice(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", date=" + date + ", descprition=" + description + "\n";
    }
    
 
}
    

