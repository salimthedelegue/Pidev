/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
import Services.EvenementService;
import Services.OrganisateurService;
import entities.Evenement;
import entities.Organisateur;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;


public class JavaApplication1 {

    
    public static void main(String[] args) throws Exception {
        
       MaConnexion cm= MaConnexion.getinstance(); 
       Evenement e= new Evenement("party11","12/02/2021","11/03/2021", 15,"hahha", "jaw jaw","paris",6,50);
        Evenement e1= new Evenement("7afla","12/02/2024","11/03/2021", 15,"hahha", "jaw jaw","paris",4,60);
        Evenement e2= new Evenement("jaw","12/02/2024","11/03/2021", 15,"hahha", "hhqhqh","mourouj",4,70);
       EvenementService ev= new EvenementService();
       Organisateur o= new Organisateur("ahmed","association","ahmed.harrabi@esprit.tn",21258318);
       Organisateur o1= new Organisateur("noursen","club","noursen.amami@esprit.tn",97976510);
       Organisateur o2= new Organisateur("wided","club","noursen.amami@esprit.tn",97976510);
       OrganisateurService or= new OrganisateurService();
       
      
        
            //or.ajouterOrganisateur(o1);
            //or.ajouterOrganisateur(o1);
            //or.supprimerOrganisateur(3);
            // or.ModifierOrganisateur(4, o2);
            //ev.participerEvenement(5,1);
            //System.out.println( or.trierOrganisateur("nom").toString());
            
            ev.ajouterEvenemnt(e2);
            //ev.supprimerEvenemnt(6);
            //ev.ModifierEvenemnt(7, e1);
            //System.out.println( or.afficher().toString());
            
            //System.out.println( ev.TrierEvenement("nom_evenement").toString());
            System.out.println( ev.RechercherEvenement("nom_evenement","7afla").toString());
            
        
    
    
}}
