/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

/**
 *
 * @author salim
 */
import tn.esprit.model.reclamation;
import java.sql.SQLException;
import java.util.List;

public interface Rservices<T> {

    public void ajouter(T p)throws SQLException;
    public List<T> afficher()throws SQLException;
    public void modifer(T p)throws SQLException;
    public void supprimer(int id)throws SQLException;
 // public List<reclamation> rechercher(int id)throws SQLException;
  public int CountReclamation()throws SQLException;
    
    //to do 
    
}