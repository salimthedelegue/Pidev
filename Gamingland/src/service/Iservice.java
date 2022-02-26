/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.User;
import java.util.List;
/**
 *
 * @author Asus
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public interface Iservice<T> {
    public void ajouteruser(T u);
     public List<T> afficheruser();
     public void  modiferuser(T u);
    public boolean supprimeruser(T u);
public void login (User u ) ;
public List<T> findByName(int id);


}



    
    
    


