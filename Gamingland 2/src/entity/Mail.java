/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage ;

import java.util.Properties;


/**
 *
 * @author Asus
 */
public class Mail {
   public static void sendMail(String recepient,String nom) throws Exception{
        System.out.println("preparing...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "ahmed.bahrouni@esprit.tn";
        String password = "191JMT1786";
        Session session = Session.getInstance(properties,new Authenticator(){
       
            @Override
    
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);
        }
        });
        Message message = prepareMessage(session , myAccountEmail , recepient ,nom);
   Transport.send(message);
        System.out.println("ajout de client efféctué avec succés");
    }
    private static Message prepareMessage(Session session , String myAccountEmail , String recepient,String nom){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient) );
            message.setSubject("Notre client fidèle");
            message.setText("Bienvenue à gamingland  ".concat(nom));
            return message;
            
        }catch(Exception ex){
            System.out.println("erreur");
        }
        return null;
    } 
}
