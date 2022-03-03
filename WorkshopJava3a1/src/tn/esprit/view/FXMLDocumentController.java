/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import tn.esprit.model.reclamation;
import tn.esprit.model.mailing;
import tn.esprit.service.Rservices;
import tn.esprit.service.ServicesReclamation;
import tn.esprit.utils.DBcnx;
import tn.esprit.view.FXML2Controller;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Tab EAjouter;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private TextArea DescriptionAjouter;
    @FXML
    private Tab ESupprimer;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private ComboBox<Integer> list;
     Connection cnx;
     ObservableList options = FXCollections.observableArrayList ();
      ObservableList options2 = FXCollections.observableArrayList ();
    @FXML
    private Tab EModdifer;
    @FXML
    private Button ButtonModfier;
    @FXML
    private TextArea DescriptionModifier;
    @FXML
    private ComboBox<Integer> list2;
    @FXML
    private Tab EModdifer1;

    
    ServicesReclamation ps= new ServicesReclamation();
    List<reclamation> reclamations =ps.afficher();
    @FXML
    private TextField rech;
    private ListData listdata = new ListData();
   private ListData listdataRech = new ListData();
   private ListData listdataTri = new ListData();
    //@FXML
   // private TableColumn<?, ?> colonne_Date;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Label LabelBah;
    @FXML
    private Button trier;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillComboSupprimer();
        fillComboSupprimer2();
        combotri.getItems().addAll(
            "id_reclamation",
            "date_reclamation",
            "description_reclamation"
        );
       
        ServicesReclamation su = new ServicesReclamation() ;
    
    LabelBah.setText(su.afficher().toString());
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws Exception {
       
           reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setDate(LocalDateTime.now());
        reclamation.setDescription(DescriptionAjouter.getText());
        sp.ajouter(reclamation);
         mailing.sendMail("salim.ferhat@esprit.tn", "salim");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete ajouter");
     alert.show();
    }

    @FXML
    private void Supprimer(ActionEvent event) {
          int value = list.getValue();
     System.out.println(value);
     ServicesReclamation ps = new ServicesReclamation();
     ps.supprimer(value);
      
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete supprime");
     alert.show();
    }

    @FXML
    private void Modifier(ActionEvent event) {
         int value2 = list2.getValue();
        reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setId((value2));
        reclamation.setDescription(DescriptionModifier.getText());
        sp.modifer(reclamation);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete modifier");
     alert.show();
    }

    @FXML
    private void GetID(ActionEvent event) {
        int value = list.getValue();
         System.out.println(value);
    }
    
    private void fillComboSupprimer() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from reclamation ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options2.add(rs.getInt("id_reclamation"));
                
            }
            list.setItems(options2);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void fillComboSupprimer2() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from reclamation ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getInt("id_reclamation"));
                
            }
            list2.setItems(options);
        } catch (SQLException ex) {
            //Logger.getLogger(tn.esprit.view.FXMLDocumentController.class.G).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GetID2(ActionEvent event) {
         int value2 = list2.getValue();
         System.out.println(value2);
    }

    @FXML
    private void ChercherAfficher(ActionEvent event) {
       ServicesReclamation su = new  ServicesReclamation() ;
    
    LabelBah.setText(su.afficherRech(combotri.getSelectionModel().getSelectedItem(),rech.getText()).toString());
    }

    @FXML
    private void RechercheTyped(KeyEvent event) {
    }

    @FXML
    private void trierbouton(ActionEvent event) {
    
     ServicesReclamation su = new  ServicesReclamation() ;
    
    LabelBah.setText(su.trieraff(combotri.getSelectionModel().getSelectedItem()).toString());
    
    
    }


    
    
}
