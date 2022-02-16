/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.servicesMaintenance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DB.DBcnx;
import entites.reclamation;
/**
 *
 * @author salim
 */
public class ServicesM implements SMservices<servicesMaintenance> {
     private  Connection cnx ;
    public ServicesM(){
      cnx =DBcnx.getInstance().getCnx();
    
}

    @Override
    public void ajouter(servicesMaintenance m) {
          try {
             String querry="INSERT INTO `servicesm`(`id_service`, `departement`,`nbr_employes`) VALUES ('"+m.getId_service()+"','"+m.getDepartement()+"','"+m.getNbr_employe()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<servicesMaintenance> afficher() {
      List<servicesMaintenance> servicesMaintenances = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `servicesm`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
          servicesMaintenance m = new servicesMaintenance();
            m.setId_service(rs.getInt(1));
            m.setDepartement(rs.getString("departement"));
            m.setNbr_employe(rs.getInt(3));
           // p.setIdservice(rs.getInt(1));
            //p.setIdCommande(rs.getInt(1));
            
            
            
            servicesMaintenances.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return servicesMaintenances ;
    }

    @Override
    public void modifer(servicesMaintenance m) {
         try {
            String req = "UPDATE servicesm SET departement='" + m.getDepartement() +"', nbr_employes='" + m.getNbr_employe()  + "' WHERE id_service=" + m.getId_service();
          Statement stm =cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Le service est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM servicesm where id_service=" +  id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("le service est  supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}   
}
