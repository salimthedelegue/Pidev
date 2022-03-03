/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import Entities.Article;
import Entities.Magazine;
import Services.ArticleService;
import Services.MagazineService;

/**
 *
 * @author nours
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         MaConnexion cm= MaConnexion.getinstance(); 
         Magazine m1=new Magazine("kalila","horreur","img/img.jpg","ahaaaaa");
       //  Magazine m2=new Magazine("demna1123","horreur","img/img2.jpg","baaaaaab");
         Article a1=new Article("tiit","ahahhha","gee","jhghs","12/02/2022",2);
          MagazineService mag= new MagazineService();
          ArticleService ar=new ArticleService();
          System.out.println(m1);
          //mag.ajouterMagazine(m2);
          //ar.ajouterArticle(a1);
         // ar.signalerArticle(4, "fih klem zeyed");
          //ar.ajouterArticle(a1);
          //ar.supprimerArticle(1);
          //ar.modifierArticle(4, a1);
          //ar.rateArticle(4, 5);
          //System.out.println(ar.afficher());
         // mag.ajouterMagazine(m1);
          //mag.supprimerMagazine(5);
          //mag.modifierMagazine(4, m2);
            // System.out.println( mag.afficher().toString());
    }
    
}
