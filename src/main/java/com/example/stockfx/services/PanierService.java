/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

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
public class PanierService{
        private Statement ste,ste1;
    private PreparedStatement pst;
    private ResultSet rs;
    private PreparedStatement statement;
    private ResultSet rs1;
    private Connection conn;
    public static int i;
    
        List<Produit> listproduit = new ArrayList<Produit>(); 

    public PanierService() {
            conn = MyDB.getInstance().getConnection();

    }
    
 public boolean verifproduitpanier(int id_client,int id_produit)
 {
  int i=-1;
         
        String req = "SELECT id_produit FROM commade_produit join commande on commade_produit.id_commande=commande.id_commande where commande.id_client = "+id_client +" and commande.type = 0 and commade_produit.id_produit = "+ id_produit;
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
                      while(rs.next()){
return true;           }
                      

        
 }          catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return false;
 }
    
 
  public boolean verifpaniervide(int id_client)
 {
         
        String req = "SELECT id_produit FROM commade_produit join commande on commade_produit.id_commande=commande.id_commande where commande.id_client = "+id_client +" and commande.type = 0 ";
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
                      while(rs.next()){
return true;           
                      }

        
 }          catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return false;
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
  
  
  
     public Float prixtotalepanier(int id_client)
   {
        String req = "SELECT SUM(prix_produit) FROM produit join commade_produit on produit.id_produit = commade_produit.id_produit join commande on commade_produit.id_commande=commande.id_commande where commande.id_client = "+id_client +" and commande.type = 0";

        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
                      while(rs.next()){
           return rs.getFloat(1);
           }



        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
            return -1f;
   }
    
  
  
  public void modifiercommande(int id_com,float prix)
  {
              try {
            String query="UPDATE commande SET "
                    + "`prix_totale`=?"
                   + " WHERE id_commande=?;";
            PreparedStatement pst=conn.prepareStatement(query);
                        pst = conn.prepareStatement(query);
            pst.setFloat(1, prix);
            pst.setInt(2, id_com);
            pst.executeUpdate();
            System.out.println("modifié avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  
    public void ajouterlisteproduitbase(int id_client,Produit p) throws SQLException
    {
        if(verifpaniervide(id_client)==false){
        int i = 0;
        
         PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO commande(id_client) values (?)", PreparedStatement.RETURN_GENERATED_KEYS);
preparedStatement.setInt(1, id_client);
preparedStatement.executeUpdate();
ResultSet rs = preparedStatement.getGeneratedKeys();
if(rs.next())
{
    i=rs.getInt(1);
    
}

    System.out.println(p.getId_produit());
                PreparedStatement preparedStatement1 = conn.prepareStatement("INSERT INTO commade_produit(id_commande,id_produit) values (?,?)");
preparedStatement1.setInt(1, i);
preparedStatement1.setInt(2, p.getId_produit());
preparedStatement1.executeUpdate();

modifiercommande(i, prixtotalepanier(id_client));

        }
        else
        {
            if(verifproduitpanier(id_client,p.getId_produit())==true)
            {
                System.out.println("produit existe");
            }
            else
            {
                      PreparedStatement preparedStatement1 = conn.prepareStatement("INSERT INTO commade_produit(id_commande,id_produit) values (?,?)");
                      preparedStatement1.setInt(1, getidcommande(id_client));
                      preparedStatement1.setInt(2, p.getId_produit());
                      preparedStatement1.executeUpdate();
                      modifiercommande(getidcommande(id_client), prixtotalepanier(id_client));
                      
                          
            }
        }


    


 }
    public List<Produit> afficherproduitpanier2(int id_client) {
        
        String req = "select * from produit join commade_produit on produit.id_produit = commade_produit.id_produit join commande on commande.id_commande = commade_produit.id_commande where commande.id_client = "+id_client + " and commande.type = 0 ";

        List<Produit> list=new ArrayList<>();
        PanierService ps=new PanierService();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){

               list.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getFloat(6), rs.getString(7)));
           }
        }   catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return list;
    }
           
    public List<Produit> afficherproduitpanier(int id_client) {
        
        String req = "select * from produit join commade_produit on produit.id_produit = commade_produit.id_produit join commande on commande.id_commande = commade_produit.id_commande where commande.id_client = "+id_client + " and commande.type = 0 ";

        List<Produit> list=new ArrayList<>();
        PanierService ps=new PanierService();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
  if(ps.verifvalide(rs.getInt(1))==false)
       {
           ps.supprimerproduitpanier(id_client,rs.getInt(1));
           list=afficherproduitpanier2(id_client);
       }
  else{
                     list.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getFloat(6), rs.getString(7)));

  }
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
/*public void supprimerproduitpanier(int id_client,Produit p)
{

        String req = "DELETE FROM commande_produit WHERE id_produit = "+p.getId_produit()+" and id_produit in (select id_produit from commande_produit join commande on commande_produit.id_commande=commande.id_commande join user on commande.id_client = "+id_client+" )";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
    
 */
    
    
   public void supprimerproduitpanier(int id_client,int id_produit)
   {
       String req = "DELETE FROM commade_produit where id_commande = "+getidcommande(id_client)+" and id_produit = "+id_produit;
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            if(verifpaniervide(id_client)==false){
                String req1="DELETE FROM commande WHERE id_client = "+id_client+" and commande.type = 0 ";
            ste1 = conn.createStatement();
            ste1.executeUpdate(req1); 
                
            }
            modifiercommande(getidcommande(id_client), prixtotalepanier(id_client));

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
   }

    

  public boolean verifvalide(int id_produit)
        {
            
                  String req = "select * from produit where id_produit = "+id_produit+" and valide = 1";

        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               return true;
           }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
}
    
   
}
