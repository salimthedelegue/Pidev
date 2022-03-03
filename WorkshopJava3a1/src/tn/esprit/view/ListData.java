/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import tn.esprit.service.ServicesReclamation;
import tn.esprit.model.reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SHS TECH
 */
public class ListData {
      private ObservableList<reclamation> magzines=FXCollections.observableArrayList();

    public ListData() {
        
        ServicesReclamation pdao=new ServicesReclamation();
        magzines=  pdao.afficher();
        System.out.println(magzines);
    }
    public ListData(String critere,String rech) {
        
        ServicesReclamation pdao=new  ServicesReclamation ();
        magzines=  pdao.afficherRech(critere, rech);
        System.out.println(magzines);
    }
    // public ListData(String critere) {
        
       //  ServicesReclamation  pdao=new  ServicesReclamation ();
      //  magzines=  pdao.afficherTri(critere);
      //  System.out.println(magzines);
   // }

   
    
    public ObservableList<reclamation> getMagazines(){
        return magzines;
    }
}
