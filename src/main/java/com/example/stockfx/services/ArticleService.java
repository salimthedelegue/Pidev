/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.stockfx.entities.Article;
import com.example.stockfx.JavaMailUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;


/**
 *
 * @author nours
 */
public class ArticleService {
    Connection cnx;
    Statement ste;

    public ArticleService() {
        cnx= MyDB.getInstance().getConnection();
    }
     public ObservableList<Article> getArticlesMag(int ref_mag) {
         ObservableList<Article> articles =FXCollections.observableArrayList();  
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article` where ref_magazine="+ref_mag;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Article a = new Article();
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
           
         
            
            
            
            articles.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  articles;
   
    }
      public Article getSelected() {
        //ObservableList<Magazine> magazines =FXCollections.observableArrayList();     
    Article a = new Article();
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article` where selected=1";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
             
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
            
             
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
      return a;
      }
     public ObservableList<Article> afficher() {
        ObservableList<Article> articles =FXCollections.observableArrayList();  
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Article a = new Article();
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
           
         
            
            
            
            articles.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  articles;
   
    }
        public ObservableList<Article> afficherRech(String critere,String rech) {
        ObservableList<Article> articles =FXCollections.observableArrayList();  
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article` where "+critere+" like '"+rech+"%'";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Article a = new Article();
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
           
         
            
            
            
            articles.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  articles;
   
    }
        public Article returnArt(int ref) {
        
     Article a = new Article();
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article` where ref_article="+ref;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
           
         
            
            
            
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  a;
   
    }
          public ObservableList<Article> afficherTri(String critere) {
        ObservableList<Article> articles =FXCollections.observableArrayList();  
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `article` order by "+critere;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Article a = new Article();
            a.setRef_article(rs.getInt(1));
            a.setTitre_article(rs.getString(2));
            a.setContenu_article(rs.getString(3));
            a.setAuteur_article(rs.getString(4));
            a.setGenre_article(rs.getString(5));
            a.setDate_article(rs.getString(6));
            a.setRef_magazine(7);
           
         
            
            
            
            articles.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  articles;
   
    }
    public void ajouterArticle(Article a){  
        String sql="insert into article(titre_article,contenu_article,auteur_article,genre_article,date_article,ref_magazine) VALUES('"+a.getTitre_article()+"','"+a.getContenu_article()+"','"+a.getAuteur_article()+"','"+a.getGenre_article()+"','"+a.getDate_article()+"',"+a.getRef_magazine()+")";
       
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Article ajouté..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void deselectionner(){  
       String sql="update article set selected=0";
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public int returnVues(int ref){
        int v=0;
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT vues FROM `article` where ref_article="+ref;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           v=rs.getInt(1);
          
           
         
            
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  v;
     }
       public void incrementerVues(int ref){  
       String sql="update article set vues=vues+1 where ref_article="+ref;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void selectionner(int ref){  
       String sql="update article set selected=1 where ref_article="+ref;
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    public void modifierArticle(int ref,Article a){  
        String sql="update article set titre_article='"+a.getTitre_article()+"',contenu_article='"+a.getContenu_article()+"',auteur_article='"+a.getAuteur_article()+"',genre_article='"+a.getGenre_article()+"',date_article='"+a.getDate_article()+"',ref_magazine="+a.getRef_magazine()+" where ref_article="+ref;
        System.out.println(sql);
       
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Article modifié..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
     public void supprimerArticle(int ref){  
        String sql="delete from article where ref_article="+ref;
        
        try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Article supprimé..");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
      public void rateArticle(int ref_article,int rate){
         String sql="insert into rating VALUES("+ref_article+","+rate+")";
               try {
            ste=cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Note enregistré..");
            System.out.println("Rate totale: "+MoyenneRate(4));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
      public double MoyenneRate(int ref) {
        
    double moy=0;
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT AVG(rate) FROM `rating` where ref_article="+ref;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
           moy=rs.getFloat(1);
            
         
            
            
            
           
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
         return  moy;
   
    }
      public void signalerArticle(int ref_article,String description) throws Exception
      {
          JavaMailUtil.sendMail("ahmed.harrabi@esprit.tn",ref_article,description);
      }
      
}
