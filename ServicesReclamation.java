/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.reclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DB.DBcnx;

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

    @Override
    public List<reclamation> afficher() {
      List<reclamation> reclamations = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `reclamation`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            reclamation p = new reclamation();
            p.setId(rs.getInt(1));
            p.setDate(rs.getString("date_reclamation"));
            p.setDescription(rs.getString(3));
           // p.setIdservice(rs.getInt(1));
            //p.setIdCommande(rs.getInt(1));
            
            
            
            reclamations.add(p);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  reclamations;
    }

    @Override
    public void modifer(reclamation p) {
        try {
            String req = "UPDATE reclamation SET date_reclamation='" + p.getDate() +"', description_reclamation='" + p.getDescription()  + "' WHERE id_reclamation=" + p.getId();
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

   
    
}
