/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author ahmed amine
 */
public final class User {
  public static int id_user; 
  private String mdp ;
  private String nom;
  private static User instance;

    public User(int id_user) {
        this.id_user = id_user;
    }

    public static User getInstace() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User() {
    }
  
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

}
