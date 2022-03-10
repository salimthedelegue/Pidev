/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameland2;

import GUI.mailUtil;
import entite.Produit;
import entite.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.CommandeService;
import service.PanierService;
import service.ProduitService;
import utils.DataSource;

/**
 *
 * @author ahmed amine
 */
public class Gameland2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
                
        ProduitService pser=new ProduitService();
        PanierService pan=new PanierService();
        //System.out.println(pan.verifvalide(65));
                   //mailUtil.sendMail("ahmedamine.nafti@esprit.tn" ,"ahmedamine.nafti@esprit.tn" , "211JMT2235", "test", "test",null);
/*List<String> list=pser.getMails();
            for(int i=0;i<list.size();i++)
               
           mailUtil.sendMail(list.get(i),"ahmedamine.nafti@esprit.tn" , "211JMT2235", "test", "test",null);
      */          
       // System.out.println(pser.getcount());
        // TODO code application logic here
      //  DataSource d =new DataSource();
      
                /*Produit p1=new Produit(42,"hjb", "hjb", "hjb", "acccccc", 13.2f, "hjb");

                ProduitService pser=new ProduitService();*/
             /*   List<Produit> listproduit = new ArrayList<Produit>(); 
listproduit.add(p);
listproduit.add(p1);*/
// pser.ajouterProduit(p);
/*PanierService panserv =new PanierService();
CommandeService c=new CommandeService();
c.afficherproduitducommande(64).forEach(e->System.out.println(e.getLien_produit()));
*/
//panserv.ajouterlisteproduitbase(1, p1);
//panserv.ajouterlisteproduitbase(1, p);
/*panserv.supprimerproduitpanier(1, 41);
                       panserv.afficherproduitpanier(1).forEach(e->System.out.println(e.toString()));
 */

        CommandeService comserv =new CommandeService();
     //   comserv.comfirmercommande(1);
       /* comserv.affichercommandecomfirmer(1).forEach(e->System.out.println(e.toString()));
        comserv.afficherproduitducommande(48).forEach(e->System.out.println(e.toString()));
comserv.afficherlistelien(48).forEach(e->System.out.println(e.toString()));
pser.readAlladmin().forEach(e->System.out.println(e.toStringadmin()));
pser.readAllclient().forEach(e->System.out.println(e.toStringclient()));*/
    }
    
}
