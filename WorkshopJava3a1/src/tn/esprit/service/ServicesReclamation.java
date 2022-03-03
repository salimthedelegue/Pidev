/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;
import tn.esprit.service.Rservices;
import tn.esprit.model.reclamation;
import java.sql.Connection;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.utils.DBcnx;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author salim
 */
public class ServicesReclamation implements Rservices<reclamation>  {
    private  Connection cnx ;
    public ServicesReclamation(){
      cnx =DBcnx.getInstance().getCnx();
       
         
}

    @Override
    public void ajouter(reclamation p) {
        try {
             String querry="INSERT INTO `reclamation`(`id_reclamation`, `date_reclamation`,`description_reclamation`) VALUES ('"+p.getId()+"','"+p.getDate()+"','"+p.getDescription()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
    

    
    public ObservableList<reclamation> afficher() {
        ObservableList<reclamation> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `reclamation`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            reclamation m = new reclamation();
               m.setId(rs.getInt(1));
           // m.setDate(rs.getString(2));
            m.setDescription(rs.getString(3));
            //m.setImage_magazine(rs.getString(4));
           // m.setDescription_magazine(rs.getString(5));
            
            
             
            magazines.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
    
    
    
     public  ObservableList<reclamation> afficherRech(String critere,String rech) {
       ObservableList<reclamation> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `reclamation` where "+critere+" like '"+rech+"%'";
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            reclamation m = new reclamation();
            m.setId(rs.getInt(1));
            //m.setDate(rs.getString(2));
            m.setDescription(rs.getString(3));
            //.setImage_magazine(rs.getString(4));
            //m.setDescription_magazine(rs.getString(5));
            
            
         
            
            magazines.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
    @Override
    public void modifer(reclamation p) {
        try {
            String req = "UPDATE reclamation SET  description_reclamation='" + p.getDescription()  + "' WHERE id_reclamation=" + p.getId();
          Statement stm =cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("La reclamation est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @Override
     public void supprimer(int id) {
         try {
            String req = "DELETE FROM reclamation where id_reclamation=" +  id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public int CountReclamation() {
         int CountReclamation = 0;
        try {
            
            

            String req ="SELECT COUNT(*) FROM reclamation;";
            Statement st2 = cnx.createStatement();

            ResultSet rs2ResultSet = st2.executeQuery(req);
            rs2ResultSet.first();
                System.out.println(rs2ResultSet.getInt(1));
            CountReclamation = rs2ResultSet.getInt(1);
            rs2ResultSet.close();
            
        } catch (SQLException ex) {
            System.out.println("this is count relamation non approuve || Connexion à la base de données impossible , " + ex.getMessage());
        } 
        return CountReclamation;
       
    }
    public List<reclamation> sortById(){
         List<reclamation> reclamations=afficher();
         List<reclamation> resultat=reclamations.stream().sorted(Comparator.comparing(reclamation::getId)).collect(Collectors.toList());
         return resultat;
     }
 public List<reclamation> sortByDate(){
         List<reclamation> reclamations=afficher();
         List<reclamation> resultat=reclamations.stream().sorted(Comparator.comparing(reclamation::getDate)).collect(Collectors.toList());
         return resultat;
         
     }
 
 public  ObservableList<reclamation> trieraff(String critere) {
       ObservableList<reclamation> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `reclamation` ORDER BY "+critere;
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            reclamation m = new reclamation();
            m.setId(rs.getInt(1));
            //m.setDate(rs.getString(2));
            m.setDescription(rs.getString(3));
            //.setImage_magazine(rs.getString(4));
            //m.setDescription_magazine(rs.getString(5));
            
            
         
            
            magazines.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
   
    
}
