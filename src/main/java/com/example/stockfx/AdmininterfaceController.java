/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.example.stockfx.entities.Pdf;
import com.example.stockfx.entities.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import com.example.stockfx.services.Serviceuser;

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
    @FXML
    private Button PDF;
    @FXML
    private Button exel;

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
         Notifications notificationBuilder = Notifications.create()
        .title("Information").text("le user a été modifier").graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    
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

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("list of users");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(Serviceuser.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 

    @FXML
    private void exel(ActionEvent event) throws IOException {
       
        File file = new File("");
        
       
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
        
        file = new File("C:\\Users\\Sana\\Downloads\\v2withtableview\\Stockfx\\src\\main\\java\\com\\example\\stockfx\\GUI\\user.xlsx");
        if(file.exists()) desktop.open(file);
              Serviceuser su = new Serviceuser() ;
           

        su.excel();
    }
    
    
    
    
}
