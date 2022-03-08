/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author salim
 */
public class DBcnx {
       public final String url="jdbc:mysql://127.0.0.1:3306/test2";
    public final String login="root";
    public final String pwd="";
    
    Connection connexion;
    public static DBcnx instance;
     private DBcnx(){
        try {
            connexion =DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnx etablie ...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERROOOOOOOOOOR");
            
        }
    }
    
    
    public static DBcnx getInstance (){
        if (instance == null)
            instance = new DBcnx();
        
        return instance;
    }
    
    public Connection getCnx(){
        return connexion;
    }
    
    
    
}

    


