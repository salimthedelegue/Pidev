/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private TextField idadmin;
    @FXML
    private Label listu;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField rech;
    @FXML
    private Button btnTrier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         combobox.getItems().addAll(
            "nom",
            "prenom",
            "email"
        );
             Serviceuser su = new Serviceuser() ;
    
    listu.setText(su.afficheruser().toString());
    }    


    @FXML
    private void modifer(ActionEvent event) {
    
        StringBuilder errors=new StringBuilder();
        if(idadmin.getText().trim().isEmpty()){
            errors.append("entre l'id de l'utilisateur à modifier\n");
        }
        if(emailadmin.getText().trim().isEmpty() ){
            errors.append("entrer l'email l\n");
        }
        if(mdpadmin.getText().trim().isEmpty()){
            errors.append("entre le mot de passe \n");
        }
         if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
        
 User u = new User();
 Serviceuser su = new Serviceuser() ;        
 u.setId_user(Integer.parseInt(idadmin.getText()));
        u.setEmail(emailadmin.getText());
        u.setMdp(mdpadmin.getText());
        
        su.modiferuser(u);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("modification éffectuée");
                alert.showAndWait();
        
        
        
    }
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

    @FXML
    private void afficherRech(KeyEvent event) {
         

       
         Serviceuser su = new Serviceuser() ;
    
    listu.setText(su.Rechercheruser(combobox.getSelectionModel().getSelectedItem(),rech.getText()).toString());
    }

    @FXML
    private void afficherTri(ActionEvent event) {
           Serviceuser su = new Serviceuser() ;
    listu.setText(su.Trieruser(combobox.getSelectionModel().getSelectedItem()).toString());
  
    }
    
}
