/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

import com.example.stockfx.entities.Commande;
import com.example.stockfx.entities.commade_produit;
import com.example.stockfx.entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author ahmed amine
 */

public class CommandeService {
        private Connection conn;
        private Statement ste,ste1;
    private ResultSet rs;

    public CommandeService() 
    {
                        conn = MyDB.getInstance().getConnection();

    }

public void comfirmercommande(int id_client)
{
        try {
            String query="UPDATE commande SET "
                    + "`type`=?,"
                    + "`date_commande`=?,"
                    + "`adresse_livraison`=?"
                   + " WHERE id_commande=?";
            PreparedStatement pst=conn.prepareStatement(query);
                        pst = conn.prepareStatement(query);
            pst.setInt(1,1);
Date date = new Date(Calendar.getInstance().getTime().getTime());

            pst.setDate(2,date);
            pst.setString(3, get_adresse(id_client));
            pst.setInt(4, getidcommande(id_client));
            pst.executeUpdate();
            System.out.println("modifié avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public String get_adresse(int id_client){
           String req = "select adresse_user from user where user.id_user = "+id_client;

        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
                      while(rs.next()){
           return rs.getString(1);
           }
        }   catch (SQLException ex) {
                Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
}
   
    public int getidcommande(int id_client)
  {
      int i=-1;
         
        String req = "SELECT commade_produit.id_commande FROM commade_produit join commande on commade_produit.id_commande=commande.id_commande where commande.id_client = "+id_client +" and commande.type = 0 ";
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
                      while(rs.next()){
                          
return rs.getInt(1);          
                      }
                          
 }          catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
      return i; 
  }  
    
    
    public List<Commande> affichercommandecomfirmer(int id_client)
    {
        String req="select * from commande where id_client = " +id_client + " and commande.type = 1 order by commande.prix_totale ";

        List<Commande> listc=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               listc.add(new Commande(rs.getInt(1),rs.getInt(2),rs.getDate(3), rs.getString(4),rs.getFloat(5)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listc;
        
    }
    
    public List<Produit> afficherproduitducommande(int id_commande){
               String req = "select * from produit join commade_produit on produit.id_produit = commade_produit.id_produit join commande on commande.id_commande = commade_produit.id_commande where commande.id_commande =  "+id_commande;

        List<Produit> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
//    public Produit(int id_produit, String nom_produit, String description_produit, String image_produit, String lien_produit, float prix_produit, String categorie_produit) {

               list.add(new Produit(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getFloat(6), rs.getString(7)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<String> afficherlistelien(int id_commande){
         List<String> list=new ArrayList<>();
         String req = "select produit.lien_produit from produit join commade_produit on produit.id_produit = commade_produit.id_produit join commande on commande.id_commande = commade_produit.id_commande where commande.id_commande =  "+id_commande;
          try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(rs.getString(1));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
                
    }
    
    public Commande affichercommande(int id_commande)
    {
        String req="select * from commande where id_commande = " +id_commande;
Commande c = null;

        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               c=new Commande(rs.getInt(1),rs.getInt(2),rs.getDate(3), rs.getString(4),rs.getFloat(5));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
        
    }
    
    

    
    
    
    
    
}

    
