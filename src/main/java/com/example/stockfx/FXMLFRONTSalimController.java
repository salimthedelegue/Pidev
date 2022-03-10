/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.example.stockfx.entities.mailing;
import com.example.stockfx.entities.reclamation;
import com.example.stockfx.entities.servicesMaintenance;
import com.example.stockfx.services.ServicesM;
import com.example.stockfx.services.ServicesReclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class FXMLFRONTSalimController implements Initializable {

    @FXML
    private TextArea DescriptionAjouterFront;
    @FXML
    private Button ButtonAjouterFront;
    @FXML
    private ComboBox<String> listFrontR;
     Connection cnx;
ObservableList options6 = FXCollections.observableArrayList ();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboajoutDep();
        // TODO
    }    

    @FXML
    private void AjouterFront(ActionEvent event) throws Exception {
        String value = listFrontR.getValue();
           reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setDate(LocalDateTime.now());
        reclamation.setDescription(DescriptionAjouterFront.getText());
//        reclamation.setIdservice();
        
            
        System.out.println("not null");
              
      //  reclamation.setIdservice();
//      reclamation rec = sp.rechercherParDep(value);
       ServicesM service = new ServicesM();
        servicesMaintenance maint = service.rechercherParDep(value);
      reclamation.setIdservice(maint.getId_service());
      
        sp.ajouter(reclamation);
         mailing.sendMail("salim.ferhat@esprit.tn", "salim");
         //SmsApi.sendSMS("+21658896300","Bienvenu dans notre application");
         
         
         Notifications notificationBuilder = Notifications.create()
        .title("Information").text("la reclamation a été ajouter").graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
         
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete ajouter");
     alert.show();
    }

    @FXML
    private void GetdepFront(ActionEvent event) {
         String value = listFrontR.getValue();
         System.out.println(value);
    
    }
    
    
    
    
     private void fillComboajoutDep() {
    try {
           
            cnx = MyDB.getInstance().getConnection();
            String req = " select * from servicesm ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options6.add(rs.getString("departement"));
                
            }
            listFrontR.setItems(options6);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
    
}
