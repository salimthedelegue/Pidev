/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

import com.example.stockfx.entities.Organisateur;
import com.example.stockfx.JavaMailUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.stockfx.javaMail;
import utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nours
 */
public class OrganisateurService {
    Connection cnx;
    Statement ste;
    public OrganisateurService(){
        cnx= MyDB.getInstance().getConnection();
    }
    
    public void ajouterOrganisateur(Organisateur o) throws Exception{
        String sql="insert into organisateur(nom,type,email_organisateur,numtel_organisateur) VALUES('"+o.getNom()+"','"+o.getType()+"','"+o.getEmail()+"',"+o.getNumTel_organisateur()+")";
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Organisateur ajouté..");
            javaMail.sendMail(o.getEmail(),o.getNom());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
       
            
       
    }
   
        ; 
    public ObservableList<Organisateur> afficher() {
      ObservableList<Organisateur> organisateurs =FXCollections.observableArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `organisateur`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Organisateur o = new Organisateur();
            o.setId_organisateur(rs.getInt(1));  
            o.setNom(rs.getString(2));
            o.setType(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setNumTel_organisateur(rs.getInt(5));
            
            
            
            organisateurs.add(o);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  organisateurs;
   
    }
       public ObservableList<Organisateur> afficherOrg() {
      ObservableList<Organisateur> organisateurs =FXCollections.observableArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT  nom FROM `organisateur`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Organisateur o = new Organisateur();
            o.setId_organisateur(rs.getInt(1));  
            o.setNom(rs.getString(2));
            o.setType(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setNumTel_organisateur(rs.getInt(5));
            
            
            
            organisateurs.add(o);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  organisateurs;
   
    }
     public void supprimerOrganisateur(int id){  
        String sql="delete from organisateur where id_organisateur="+id;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Organisateur supprimé..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void ModifierOrganisateur(int id,Organisateur o){  
        String sql="update organisateur set nom='"+o.getNom()+"',"+"type='"+o.getType()+"',"+"email_organisateur='"+o.getEmail()+"',"+"numtel_organisateur="+o.getNumTel_organisateur()+" where id_organisateur="+id;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Organisateur modifié..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public ObservableList<Organisateur> trierOrganisateur(String critere) {
        ObservableList<Organisateur> organisateurs =FXCollections.observableArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `organisateur` order by "+critere;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Organisateur o = new Organisateur();
           o.setId_organisateur(rs.getInt(1));  
            o.setNom(rs.getString(2));
            o.setType(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setNumTel_organisateur(rs.getInt(5));
            
            
            
            organisateurs.add(o);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  organisateurs;
   
    }
   
   public  ObservableList<Organisateur> rechercherOrganisateur(String critere ,String rech) {
      ObservableList<Organisateur> organisateurs =FXCollections.observableArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `organisateur` where "+critere+" like '"+rech+"%'";
        

        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Organisateur o = new Organisateur();
                 o.setId_organisateur(rs.getInt(1));         
            o.setNom(rs.getString(2));
            o.setType(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setNumTel_organisateur(rs.getInt(5));
            
            
            
            organisateurs.add(o);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  organisateurs;
   
    }
   public Organisateur RecupererOrganisateur(int id) {
         Organisateur o = new Organisateur();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `organisateur` where id_organisateur="+id;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           
             o.setId_organisateur(rs.getInt(1));         
            o.setNom(rs.getString(2));
            o.setType(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setNumTel_organisateur(rs.getInt(5));
            
            
            
            
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  o;
   
    }
}
