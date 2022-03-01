/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Role;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Serviceuser;
import static sun.security.jgss.GSSUtil.login;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel_user;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField adresse_user;
    @FXML
    private AnchorPane arlogin;
    @FXML
    private AnchorPane arsignup;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 Serviceuser su = new Serviceuser() ;
    @FXML
    private void ajouter(ActionEvent event) {
          
              StringBuilder errors=new StringBuilder();
        
        if(prenom.getText().trim().isEmpty()){
            errors.append("entre votre prenom\n");
        }
        if(numtel_user.getText().trim().isEmpty() ){
            errors.append("entre votre num tel\n");
        }
        if(nom.getText().trim().isEmpty()){
            errors.append("entre votre nom \n");
        }
        if(email.getText().trim().isEmpty()){
            errors.append("entre votre email\n");
        }
        if(mdp.getText().trim().isEmpty()){
            errors.append("entre votre mdp\n");
        }
        if(adresse_user.getText().trim().isEmpty() ){
            errors.append("entre votre adress user\n");
        }
        
        if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            User u =new User();
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
           
            u.setAdresse_user(adresse_user.getText());
            u.setEmail(email.getText());
            u.setMdp(mdp.getText());
            
           
            u.setRole(Role.CLIENT);
           su.ajouteruser(u);

        }
      
        
    
    
    }

    @FXML
    private void login(ActionEvent event) {
        arlogin.setVisible(true);
        arsignup.setVisible(false);
    }

    @FXML
    private void signup(ActionEvent event) {
        arlogin.setVisible(false);
        arsignup.setVisible(true);
    }
@FXML 
 private void loginn(ActionEvent event) throws IOException {
   
     
   Serviceuser su = new Serviceuser() ;
        User u = new User();

        u.setEmail(username.getText());
        u.setMdp(password.getText());
        
        if (su.login(u.getEmail(), u.getMdp())) {

           
            Parent home_page_parent;
           
            if (su.checkRole(username.getText()).equals("Admin"))  {
//               Session.getFirstInstance(Session.getUser());
//               
//                    System.out.println(Session.getUser().getId_user());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Admin");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/admininterface.fxml"));
                home_page_parent = loader.load();
                AdmininterfaceController ad = loader.getController();
                username.getScene().setRoot(home_page_parent);
//                Scene home_page_scene = new Scene(home_page_parent);
//                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                app_stage.hide();
//                app_stage.setScene(home_page_scene);
//                app_stage.show();

            } 
        else    if (su.checkRole(username.getText()).equals("Client"))  {
                 //Session.getFirstInstance(Session.getUser());
               //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed! as client");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/clientinterface.fxml"));
                home_page_parent = loader.load();
                ClientinterfaceController da = loader.getController();
                username.getScene().setRoot(home_page_parent);

            }
         
            
        }

    } 
     
     
     
        
 } 
        
        
    
    

