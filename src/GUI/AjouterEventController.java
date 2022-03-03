/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.EvenementService;
import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nours
 */
public class AjouterEventController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_dateD;
    @FXML
    private TextField txt_dateF;
    @FXML
    private TextField txt_image;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_emplacement;
    @FXML
    private TextField txt_nbr;
    @FXML
    private Button btnajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addEvent(ActionEvent event) {
        String nom=txt_nom.getText();
        String dateD=txt_dateD.getText();
        String dateF=txt_dateF.getText();
        String image=txt_image.getText();
        float prix=Float.parseFloat(txt_prix.getText());
        String description=txt_description.getText();
        String emplacement=txt_emplacement.getText();
        int nbr=Integer.parseInt(txt_nbr.getText());
         Evenement e= new Evenement(nom,dateD,dateF, prix,image ,description,emplacement,4,nbr);
         
        EvenementService ev= new EvenementService();
        ev.ajouterEvenemnt(e);
    }
    
}
