/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.Serviceuser;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdmininterfaceController implements Initializable {

    @FXML
    private TextField mdpadmin;
    @FXML
    private TextField emailadmin;
    @FXML
    private TextField roleadmin;
    @FXML
    private TextField idadmin;
    @FXML
    private Label listu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) {
    
    Serviceuser su = new Serviceuser() ;
    listu.setText(su.afficheruser().toString());
    
    }

    @FXML
    private void modifer(ActionEvent event) {
   /* StringBuilder errors=new StringBuilder();
        
        if(idadmin.getText().trim().isEmpty()){
            errors.append("Please enter an adress\n");
        }
    
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
    if(emailadmin.getText().trim().isEmpty()  && roleadmin.getText().trim().isEmpty()&& mdpadmin.getText().trim().isEmpty())
    {
    Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" modifier au moins un atrubut !");
            alert.setContentText(errors.toString());
            alert.showAndWait();
    
    }
    
    else
    { 
        User u =new User();
        if( idadmin.getText()=u.getId_user())
        {        
    
    
    
    }    
     
    */
    }
    
     
    @FXML
    private void supprimer(ActionEvent event) {
   Serviceuser su = new Serviceuser() ;

            su.supprimeruser(Integer.parseInt(idadmin.getText()));

      StringBuilder errors=new StringBuilder();
        
        if(idadmin.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
    
    
    
    }
    
}
