/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Evenement;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EvenementService {
    Connection cnx;
    Statement ste;
    public EvenementService(){
        cnx= MaConnexion.getinstance().getCnx();
    }
    
    public void ajouterEvenemnt(Evenement e){  
        String sql="insert into evenement(nom_evenement,date_debut,date_fin,prix,image,description,emplacement,id_organisateur,nbr_max_participant) VALUES('"+e.getNom()+"','"+e.getDateD()+"','"+e.getDateF()+"',"+e.getPrix()+",'"+e.getImage()+"','"+e.getDescription()+"','"+e.getEmplacement()+"',"+ e.getId_organisateur()+","+e.getNbr_max_participant()+")";
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Evenement ajouté..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    public ObservableList<Evenement> afficher() {
         ObservableList<Evenement> evenements =FXCollections.observableArrayList(); 
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `evenement`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Evenement e = new Evenement();
            e.setRef(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setDateD(rs.getString(3));
            e.setDateF(rs.getString(4));
            e.setPrix(rs.getFloat(5));
            e.setImage(rs.getString(6));
            e.setDescription(rs.getString(7));
            e.setEmplacement(rs.getString(8));
            e.setId_organisateur(rs.getInt(9));
            e.setNbr_participant(rs.getInt(10));
            e.setNbr_max_participant(rs.getInt(11));
            
            
            
            evenements.add(e);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  evenements;
   
    }
    public void supprimerEvenemnt(int ref){  
        String sql="delete from evenement where reference="+ref;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Evenement supprimé..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void ModifierEvenemnt(int ref,Evenement e){  
        String sql="update evenement set nom_evenement='"+e.getNom()+"',"+"date_debut='"+e.getDateD()+"',"+"date_fin='"+e.getDateF()+"',"+"prix="+e.getPrix()+","+"image='"+e.getImage()+"',"+"description='"+e.getDescription()+"',"+"emplacement='"+e.getEmplacement()+"',"+"id_organisateur="+e.getId_organisateur()+","+"nbr_max_participant="+e.getNbr_max_participant()+" where reference="+ref;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Evenement modifié..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public ObservableList<Evenement> TrierEvenement(String critere) {
                ObservableList<Evenement> evenements =FXCollections.observableArrayList(); 
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `evenement` order by "+critere;
     System.out.println(querry);
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Evenement e = new Evenement();
            e.setRef(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setDateD(rs.getString(3));
            e.setDateF(rs.getString(4));
            e.setPrix(rs.getFloat(5));
            e.setImage(rs.getString(6));
            e.setDescription(rs.getString(7));
            e.setEmplacement(rs.getString(8));
            e.setId_organisateur(rs.getInt(9));
            e.setNbr_participant(rs.getInt(10));
            e.setNbr_max_participant(rs.getInt(11));
            
            
            
            
            evenements.add(e);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  evenements;
   
    }
     public ObservableList<Evenement> RechercherEvenement(String critere,String rech) {
        ObservableList<Evenement> evenements =FXCollections.observableArrayList(); 
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `evenement` where "+critere+" like '"+rech+"%'";
            System.out.println(querry);
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Evenement e = new Evenement();
            e.setNom(rs.getString(2));
            e.setDateD(rs.getString(3));
            e.setDateF(rs.getString(4));
            e.setPrix(rs.getFloat(5));
            e.setImage(rs.getString(6));
            e.setDescription(rs.getString(7));
            e.setEmplacement(rs.getString(8));
            e.setId_organisateur(rs.getInt(9));
            e.setNbr_participant(rs.getInt(10));
            e.setNbr_max_participant(rs.getInt(11));
            
            
            
            
            evenements.add(e);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  evenements;
   
    }
     public void participerEvenement(int ref_evenement,int id_user){
         String sql="insert into reservation(ref_evenement,id_user,date_reservation) VALUES("+ref_evenement+","+id_user+",'"+LocalDateTime.now()+"')";
        Evenement e=RecupererEvenement(ref_evenement);
        if(e.getNbr_participant()<e.getNbr_max_participant())
        {try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Reservation ajouté..");
            incrementerParticipant(ref_evenement);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }}else System.out.println("nbr max aqal");
     }
       public void incrementerParticipant(int ref_evenement){
         String sql="update evenement set nbr_participant=nbr_participant+1 where reference="+ref_evenement;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Reservation ajouté..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
     }
    public Evenement RecupererEvenement(int ref) {
         Evenement e = new Evenement();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `evenement` where reference="+ref;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           
            e.setNom(rs.getString(2));
            e.setDateD(rs.getString(3));
            e.setDateF(rs.getString(4));
            e.setPrix(rs.getFloat(5));
            e.setImage(rs.getString(6));
            e.setDescription(rs.getString(7));
            e.setEmplacement(rs.getString(8));
            e.setId_organisateur(rs.getInt(9));
            e.setNbr_participant(rs.getInt(10));
            e.setNbr_max_participant(rs.getInt(11));
            
            
            
            
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  e;
   
    }
     
}
