/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

import com.example.stockfx.entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author wiemhjiri
 */
public class ProduitService{

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ProduitService() {
        conn = MyDB.getInstance().getConnection();
    }



    public void ajouterProduit(Produit p) {
        String req = "insert into produit(nom_produit,description_produit,image_produit,lien_produit,prix_produit,categorie_produit) values (?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, p.getNom_produit());
            pst.setString(2, p.getDescription_produit());
            pst.setString(3, p.getImage_produit());
            pst.setString(4, p.getLien_produit());
            pst.setString(5, String.valueOf(p.getPrix_produit()));
            pst.setString(6, p.getCategorie_produit());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public void modifier(int id_amodifier, Produit p) {
        try {
            String query="UPDATE produit SET "
                    + "`nom_produit`=?,"
                    + "`description_produit`=?,"
                    + "`image_produit`=?,"
                    + "`lien_produit`=?,"
                    + "`prix_produit`=?,"
                    + "`categorie_produit`=?"
                   + " WHERE id_produit=?";
            PreparedStatement pst=conn.prepareStatement(query);
                       // pst = conn.prepareStatement(query);
            pst.setString(1, p.getNom_produit());
            pst.setString(2, p.getDescription_produit());
            pst.setString(3, p.getImage_produit());
            pst.setString(4, p.getLien_produit());
            pst.setString(5, String.valueOf(p.getPrix_produit()));
            pst.setString(6, p.getCategorie_produit());
             pst.setInt(7, id_amodifier);


            pst.executeUpdate();
            System.out.println("modifié avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }




    }
    public List<Produit> readAlladmin2(String ch,String f,int p) {
        String req = "select * from produit where valide = 1 and prix_produit > '"+p+"'";
        if (!ch.equals(""))
            req=req+" and nom_produit like '"+ch+"%' or description_produit like '"+ch+"%' and valide = 1";
        if(!f.equals("Tous"))
            req=req+" and categorie_produit='"+f+"'";
        
        List<Produit> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getFloat(6), rs.getString(7)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getMax() {
        String req = "select max(prix_produit) from produit";
        int a=-1;
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
              a= rs.getInt(1);
           }
           return a;
        } catch (SQLException ex) {
           Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
           return -1;
        }
    }
    
public List<Produit> readAlladmin() {
        String req = "select * from produit where valide = 1";

        List<Produit> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getFloat(6), rs.getString(7)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void supprimer(int id) {
        String req = "UPDATE produit SET valide = 0 WHERE id_produit = "+id;
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


        public List<Produit> readAllclient() {
        String req = "select * from produit where valide = 1";

        List<Produit> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getFloat(6), rs.getString(7)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public List<String> getMails() {
        String req = "select email from user where role = 'CLIENT' ";

        List<String> list=new ArrayList<>();
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

    public List<Produit> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public HashMap<String,Double> getcount() {
    String req  = " SELECT produit.nom_produit, COUNT(commade_produit.id_produit) Nbr from commade_produit join produit on commade_produit.id_produit = produit.id_produit join commande on commade_produit.id_commande = commande.id_commande where commande.type = 1 and produit.valide = 1 group by produit.id_produit ";

   HashMap<String,Double> map=new HashMap<String,Double>();
   try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               map.put(rs.getString(1), rs.getDouble(2));
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;


}
}
