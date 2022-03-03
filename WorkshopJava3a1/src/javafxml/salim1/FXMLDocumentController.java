/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxml.salim1;

import java.io.IOException;
import java.net.URL;
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
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.model.reclamation;
import tn.esprit.service.Rservices;
import tn.esprit.service.ServicesReclamation;
import tn.esprit.view.FXML2Controller;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class FXMLDocumentController implements Initializable {

    @FXML
     private Stage stage;
    private Scene scene;
    private Parent root;
    private Button BtnInsert;
    @FXML
    private Button BtnDelete;
    @FXML
    private Button BtnUpdate;
    @FXML
    private TextField TxtID;
    @FXML
    private TextField TxtDescprition;
    @FXML
    private TextArea listR;
    @FXML
    private Button BtnInsert1;
    @FXML
    private Button Buttonservice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    @FXML
    private void ListPersons(ActionEvent event) {
        ServicesReclamation sp= new ServicesReclamation();
        listR.setText(sp.afficher().toString());
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setDate(LocalDateTime.now());
        reclamation.setDescription(TxtDescprition.getText());
        sp.ajouter(reclamation);
    }

    @FXML
    private void supprimer(ActionEvent event) {
         reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
         sp.supprimer(Integer.parseInt(TxtID.getText()));
        
       
    }

    @FXML
    private void modifier(ActionEvent event) {
          reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setId(Integer.parseInt(TxtID.getText()));
        reclamation.setDescription(TxtDescprition.getText());
        sp.modifer(reclamation);
    }

    @FXML
    private void RetourService(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("/tn/esprit/view/FXML2.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
   stage.setTitle("Gestion Services");
  stage.show();
 
    }
}
