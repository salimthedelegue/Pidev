/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.sql.*;
/**
 *
 * @author nours
 */
public class MaConnexion {
    public String url="jdbc:mysql://localhost:3306/projetpidev";
    public String user="root";
    public String pwd="";
    private Connection cnx;
    public static MaConnexion ct;
    private MaConnexion(){
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie..");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    public static MaConnexion getinstance(){
        if(ct==null)
            ct=new MaConnexion();
            return ct;
        
    }
    public Connection getCnx() {
        return cnx;
    }
}
