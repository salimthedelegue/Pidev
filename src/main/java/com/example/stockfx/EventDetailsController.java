/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.stockfx.entities.Evenement;
import com.example.stockfx.services.EvenementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author nours
 */
public class EventDetailsController implements Initializable {

    @FXML
    private Label labNom;
    @FXML
    private ImageView imageEv;
    @FXML
    private Text labDescription;
    @FXML
    private Label labPrix;
    @FXML
    private Label labDate;
    @FXML
    private Label labDateD;
    @FXML
    private Label labDateF;
    @FXML
    private Label labPrix2;
    @FXML
    private Label labEmplacement;
    @FXML
    private Label labOrganisateur;
    @FXML
    private Button buttonParticiper;
    @FXML
    private Label labDateD1;
    @FXML
    private Label labDateF1;
    @FXML
    private Label labPrix21;
    @FXML
    private Label labEmplacement1;
    @FXML
    private Label labOrganisateur1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
  
       EvenementService ev=new EvenementService();
       Evenement e=ev.getSelected();
       labNom.setText(e.getNom());
       labPrix.setText(Float.toString(e.getPrix())+" DT");
       labPrix2.setText(Float.toString(e.getPrix()));
       labDate.setText(e.getDateD());
       labDateD.setText(e.getDateD());
       labDateF.setText(e.getDateF());
       labEmplacement.setText(e.getEmplacement());
       labDescription.setText(e.getDescription());
        Image image1=new Image(getClass().getResourceAsStream(e.getImage()));
            ImageView img=new ImageView(image1);
     
       
    imageEv.setImage(image1);
               
    }    

    @FXML
    private void participer(ActionEvent event) {
        Stage stage=(Stage)buttonParticiper.getScene().getWindow();
                 stage.close();
                 Stage PrimaryStage=new Stage();
                 
                 Parent root;
                 try {
                     root = FXMLLoader.load(getClass().getResource("Payment.FXML"));
                
                 PrimaryStage.setTitle("View stage");
                 PrimaryStage.setScene(new Scene(root));
                 PrimaryStage.show();
                  } catch (IOException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    
    
}
