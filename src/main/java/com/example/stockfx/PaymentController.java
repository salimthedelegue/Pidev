/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import com.example.stockfx.services.EvenementService;
import com.example.stockfx.services.Serviceuser;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nours
 */
public class PaymentController implements Initializable {

    @FXML
    private Button payerBtn;
    @FXML
    private TextField numCarte;
    @FXML
    private TextField expyear;
    @FXML
    private TextField expmonth;
    @FXML
    private TextField cvc;
    @FXML
    private ImageView id_visa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1=new Image(getClass().getResourceAsStream("visa-carte.jpg"));
          
     
       
    id_visa.setImage(image1);
        // TODO
    }    

    @FXML
    private void effectuerPayment(ActionEvent event) throws StripeException {
        EvenementService ev = new EvenementService();
        
        String prix=Integer.toString((int)ev.getSelected().getPrix());
        
        System.out.println(prix);
        Serviceuser su=new Serviceuser();
        boolean test=Payement.payer(su.getConnected().getEmail(), numCarte.getText(), expyear.getText(), expmonth.getText(), cvc.getText(), prix);
        if(test){
            ev.participerEvenement(ev.getSelected().getRef(), 1);
        }
        Stage stage=(Stage)payerBtn.getScene().getWindow();
                 stage.close();
                 Stage PrimaryStage=new Stage();
                 
                 Parent root;
                 try {
                     root = FXMLLoader.load(getClass().getResource("Event.FXML"));
                
                 PrimaryStage.setTitle("View stage");
                 PrimaryStage.setScene(new Scene(root,500,500));
                 PrimaryStage.show();
                  } catch (IOException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        
    }
    

