/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author salim
 */
import java.util.List;

public interface Rservices<T> {

    public void ajouter(T p);
    public List<T> afficher();
    public void modifer(T p);
    public void supprimer(int id);
    
    //to do 
    
}