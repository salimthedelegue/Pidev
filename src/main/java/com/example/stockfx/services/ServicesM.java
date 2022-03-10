/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;
import com.example.stockfx.services.SMservices;

import com.example.stockfx.entities.servicesMaintenance;
/**
 *
 * @author salim
 */
public class ServicesM implements SMservices<servicesMaintenance> {
     private  Connection cnx ;
    public ServicesM(){
        cnx = MyDB.getInstance().getConnection();
    
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

   public ObservableList<servicesMaintenance> afficher() {
        ObservableList<servicesMaintenance> servicem =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `servicesm`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            servicesMaintenance m = new servicesMaintenance();
               m.setId_service(rs.getInt(1));
            m.setDepartement(rs.getString(2));
            m.setNbr_employe(rs.getInt(3));
            //m.setImage_magazine(rs.getString(4));
           // m.setDescription_magazine(rs.getString(5));
            
            
             
            servicem.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  servicem;
   
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
    
    
     public  ObservableList<servicesMaintenance> afficherRech(String critere,String rech) {
       ObservableList<servicesMaintenance> servicem =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `servicesm` where "+critere+" like '"+rech+"%'";
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            servicesMaintenance m = new servicesMaintenance();
            m.setId_service(rs.getInt(1));
            m.setDepartement(rs.getString(2));
            m.setNbr_employe(rs.getInt(3));
            
            //.setImage_magazine(rs.getString(4));
            //m.setDescription_magazine(rs.getString(5));
            
            
         
            
            servicem.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  servicem;
   
    }
     
     
     
     public  ObservableList<servicesMaintenance> trieraff(String critere) {
       ObservableList<servicesMaintenance> servicem =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `servicesm` ORDER BY "+critere;
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            servicesMaintenance m = new servicesMaintenance();
            m.setId_service(rs.getInt(1));
            m.setDepartement(rs.getString(2));
            m.setNbr_employe(rs.getInt(3));
            //.setImage_magazine(rs.getString(4));
            //m.setDescription_magazine(rs.getString(5));
            
            
         
            
            servicem.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  servicem;
   
    }
     public servicesMaintenance rechercherParDep(String departement) {
       
        servicesMaintenance maintenance = new servicesMaintenance();
        List<servicesMaintenance> maintenances = new ArrayList<>();
        ServicesReclamation roleService = new ServicesReclamation();
        String sql ="SELECT * FROM servicesm WHERE departement=? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, departement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                maintenance.setId_service(rs.getInt(1));  
                maintenances.add(maintenance);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return maintenance;
    }
   
    
}
