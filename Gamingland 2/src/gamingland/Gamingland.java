/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamingland;
import entity.Role;
import entity.User ;
import javax.xml.transform.Source;
import service.Serviceuser;
import utils.SmsApi;

/**
 *
 * @author Asus
 */
public class Gamingland {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serviceuser su = new Serviceuser() ;
    
     //   su.ajouteruser( new User(123,"123","bo7mid","bahrouni","salah",Role.ADMIN,123,"21993177")) ;    
       // System.out.println(su.afficheruser().toString());
        // su.supprimeruser(9);    
         //    su.supprimeruser(8); 
//          su.modiferuser( new User(11,"5","bro","lol","mohamed ali","client",123,"555")) ;  
//             System.out.println(su.afficheruser().toString());
             
   //User u = new User(100000, "123456789", "", "", "salah", Role.ADMIN, 0, "");
    //         su.login(u);

  // System.out.println(su.findByName(17).toString());
      //  System.out.println(su.sortByEMAIL());
      
      //SmsApi.sendSMS("+21652778549","bienvenue");
             
                
             
  
  
    }
    
}
