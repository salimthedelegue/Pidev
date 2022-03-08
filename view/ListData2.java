/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;
import tn.esprit.service.ServicesM;
import tn.esprit.model.servicesMaintenance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.service.ServicesReclamation;

/**
 *
 * @author salim
 */
public class ListData2 {
    private ObservableList<servicesMaintenance> ServiceM1 =FXCollections.observableArrayList();
       public ListData2() {
        
        ServicesM pdao=new ServicesM();
        ServiceM1 =  pdao.afficher();
        System.out.println(ServiceM1);
    }
       
        public ListData2(String critere,String rech) {
        
        ServicesM pdao=new  ServicesM ();
        ServiceM1=  pdao.afficherRech(critere, rech);
        System.out.println(ServiceM1);
    }
    // p
       
       
        
    public ObservableList<servicesMaintenance> getServiceM(){
        return ServiceM1;
    }
}
