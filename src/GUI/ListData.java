/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Magazine;
import Services.MagazineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SHS TECH
 */
public class ListData {
      private ObservableList<Magazine> magzines=FXCollections.observableArrayList();

    public ListData() {
        
        MagazineService pdao=new MagazineService();
        magzines=  pdao.afficher();
        System.out.println(magzines);
    }
    public ListData(String critere,String rech) {
        
        MagazineService pdao=new MagazineService();
        magzines=  pdao.afficherRech(critere, rech);
        System.out.println(magzines);
    }
     public ListData(String critere) {
        
        MagazineService pdao=new MagazineService();
        magzines=  pdao.afficherTri(critere);
        System.out.println(magzines);
    }

   
    
    public ObservableList<Magazine> getMagazines(){
        return magzines;
    }
}
