/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Article;
import Entities.Magazine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javaapplication2.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author nours
 */
public class MagazineService {
      Connection cnx;
    Statement ste;
    public MagazineService(){
        cnx= MaConnexion.getinstance().getCnx();
    }
    
    public ObservableList<Magazine> afficher() {
        ObservableList<Magazine> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `magazine`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Magazine m = new Magazine();
               m.setRef(rs.getInt(1));
            m.setTitre_magazine(rs.getString(2));
            m.setGenre_magazine(rs.getString(3));
            m.setImage_magazine(rs.getString(4));
            m.setDescription_magazine(rs.getString(5));
            
            
             
            magazines.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
   
     public  ObservableList<Magazine> afficherRech(String critere,String rech) {
       ObservableList<Magazine> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `magazine` where "+critere+" like '"+rech+"%'";
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Magazine m = new Magazine();
            m.setRef(rs.getInt(1));
            m.setTitre_magazine(rs.getString(2));
            m.setGenre_magazine(rs.getString(3));
            m.setImage_magazine(rs.getString(4));
            m.setDescription_magazine(rs.getString(5));
            
            
         
            
            magazines.add(m);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
      public  Magazine returnMagazine(int rech) {
       Magazine m = new Magazine();
    
        try {
             
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `magazine` where ref_magazine="+rech;
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           
            m.setRef(rs.getInt(1));
            m.setTitre_magazine(rs.getString(2));
            m.setGenre_magazine(rs.getString(3));
            m.setImage_magazine(rs.getString(4));
            m.setDescription_magazine(rs.getString(5));
            
            
         
            
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  m;
   
    }
      public  ObservableList<Magazine> afficherTri(String critere) {
       ObservableList<Magazine> magazines =FXCollections.observableArrayList();     
    
        try {
        Statement stm =cnx.createStatement();
             String querry ="SELECT * FROM `magazine` order by "+critere;
             System.out.println(querry);
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Magazine m = new Magazine();
            m.setRef(rs.getInt(1));
            m.setTitre_magazine(rs.getString(2));
            m.setGenre_magazine(rs.getString(3));
            m.setImage_magazine(rs.getString(4));
            m.setDescription_magazine(rs.getString(5));
            
      
            
            magazines.add(m);
              
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  magazines;
   
    }
    public void ajouterMagazine(Magazine m){  
        String sql="insert into magazine(titre_magazine,genre_magazine,image_magazine,description_magazine) VALUES('"+m.getTitre_magazine1()+"','"+m.getGenre_magazine1()+"','"+m.getImage_magazine1()+"','"+m.getDescription_magazine1()+"')";
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Magazine ajouté..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    public void supprimerMagazine(int ref){
         String sql="delete from magazine where ref_magazine="+ref;
               try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Magazine supprimé..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void modifierMagazine(int ref,Magazine m){  
       String sql="update magazine set titre_magazine='"+m.getTitre_magazine1()+"',"+"genre_magazine='"+m.getGenre_magazine1()+"',"+"image_magazine='"+m.getImage_magazine1()+"',"+"description_magazine='"+m.getDescription_magazine1()+"'"+" where ref_magazine="+ref;
        
       try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Magazine mofdifié..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    
}
