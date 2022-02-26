/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.Projectbd;
/**
 *
 * @author Asus
 */
public class Serviceuser implements Iservice<User>{
private Connection cnx ;
public Serviceuser()   {
    
cnx=Projectbd.getInstance().getCnx();    
}

@Override
    public void ajouteruser(User u) {
          
        try {
             String querry="INSERT INTO `user`( `mdp`, `nom`, `prenom`, `email`, `role`, `numtel_user`, `adresse_user`) VALUES ('"+u.getMdp()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getRole()+"','"+u.getNumtel_user()+"','"+u.getAdresse_user()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    
    }

    @Override
    public List<User> afficheruser() {
      
        List<User> Users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM user";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            User u = new User();
            u.setId_user(rs.getInt(1));
            u.setMdp(rs.getString(2));
            u.setNom(rs.getString(3));
            u.setPrenom(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setRole(rs.getString(6));
            u.setAdresse_user(rs.getString(7));
            u.setNumtel_user((rs.getInt(8)));
            
            
            Users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Users;
   
    }
   public void supprimeruser(int id_user) {
        try {
            String req = "DELETE FROM user where id_user=" + id_user;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
   }


    @Override
    public boolean supprimeruser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modiferuser(User u) {
           try {
           String req = "UPDATE `user` SET  `mdp`='" + u.getMdp()+ "', `nom`='" + u.getNom()+  "', `prenom`='" + u.getPrenom()+     "', `email`='" + u.getEmail()+  "', `role`='" + u.getRole()+  "', `numtel_user`='" + u.getNumtel_user()+ "', `adresse_user`='" + u.getAdresse_user() +                "' WHERE id_user=" + u.getId_user();
            
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le user  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }

    @Override
    public void login(User u) {

        String req = "SELECT * FROM  `user` WHERE email='" + u.getEmail() +   "' and mdp ='"+ u.getMdp()+ "'" ;
    try {
        //
//        PreparedStatement st = cnx.preparedStatement(req);
     PreparedStatement ps = cnx.prepareStatement(req);
     ResultSet rs = ps.executeQuery();
     if(rs.next()){        
         System.out.println("Authentification validee pour l'utilisateur "+u.getEmail());
     }else
         System.err.println("l'utilisateur n'existe pas");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
           
           
    
         
    
    }

    @Override
    public List<User> findByName(int id) {
     List<User> users1 = new ArrayList<>();
        try {

            String req = "SELECT * FROM `user` WHERE id_user = " + id ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
            User m = new User();
            m.setId_user(rs.getInt(1));
            m.setMdp(rs.getString(2));
            m.setNom(rs.getString(3));
            m.setPrenom(rs.getString(4));
           m.setEmail(rs.getString(5));
           m.setRole(rs.getString(6));
            m.setAdresse_user(rs.getString(7));
            m.setNumtel_user((rs.getInt(8)));
            
            
            users1.add(m);
        }
              rs.close();

            } catch (SQLException ex) {
       System.out.println(ex.getMessage());
    }
          
       
        return users1;
   
    }
   public List<User> sortByEMAIL(){
         List<User> users=afficheruser();
         List<User> resultat=users.stream().sorted(Comparator.comparing(User::getEmail)).collect(Collectors.toList());
         return resultat;
     } 
   
    
    
 
    
    
    }
    

