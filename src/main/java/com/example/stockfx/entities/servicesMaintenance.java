/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.entities;

/**
 *
 * @author salim
 */
public class servicesMaintenance {
   private int id_service;
   private String departement;
   private int nbr_employe;
public servicesMaintenance(){

}
    public servicesMaintenance(int id, String departement, int nbr_employes) {
        this.id_service=id;
        this.departement=departement;
        this.nbr_employe=nbr_employes;
        
    }
   
   

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public int getNbr_employe() {
        return nbr_employe;
    }

    public void setNbr_employe(int nbr_employe) {
        this.nbr_employe = nbr_employe;
    }
   
     @Override
    public String toString() {
        return "serviceMaintenance{" + "idService=" + id_service + ", departement=" + departement + ", nombre employe=" + nbr_employe + "\n";
    }
    
}
