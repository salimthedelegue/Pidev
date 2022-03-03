/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Magazine;
import Services.MagazineService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class AcceuilController implements Initializable {

    private TextField txt_titre;
    private TextField txt_genre;
    private TextField txt_image;
    private TextField txt_description;
    @FXML
    private ListView<String> listCol1;
    @FXML
    private ListView<String> listCol2;
    @FXML
    private ListView<String> listCol3;
    @FXML
    private ListView<String> listCol4;
    @FXML
    private ListView<Integer> listCol5;
    @FXML
    private Button btnsupp;
 ObservableList<String>list=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

          MagazineService mag= new MagazineService();
           ObservableList<Magazine> magazines = mag.afficher();
             for(Magazine m : magazines){
                 
                 listCol1.getItems().add(m.getTitre_magazine1());
             }
              for(Magazine m : magazines){
                 listCol2.getItems().add(m.getGenre_magazine1());
             }
               for(Magazine m : magazines){
                 listCol3.getItems().add(m.getImage_magazine1());
             }
                for(Magazine m : magazines){
                 listCol4.getItems().add(m.getDescription_magazine1());
             }
                for(Magazine m : magazines){
                 listCol5.getItems().add(m.getRef());
             }
                
                
      
        // TODO
    }    
public void refrech()
{
    
   listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          MagazineService mag= new MagazineService();
           ObservableList<Magazine> magazines = mag.afficher();
             for(Magazine m : magazines){
                 listCol1.getItems().add(m.getTitre_magazine1());
             }
              for(Magazine m : magazines){
                 listCol2.getItems().add(m.getGenre_magazine1());
             }
               for(Magazine m : magazines){
                 listCol3.getItems().add(m.getImage_magazine1());
             }
                for(Magazine m : magazines){
                 listCol4.getItems().add(m.getDescription_magazine1());
             }
                for(Magazine m : magazines){
                 listCol5.getItems().add(m.getRef());
             }
}
    private void addMagazine(ActionEvent event) {
        String titre=txt_titre.getText();
        String genre=txt_genre.getText();
        String image=txt_image.getText();
        String description=txt_description.getText();
         Magazine m1=new Magazine(titre,genre,image,description);
         MagazineService mag= new MagazineService();
         mag.ajouterMagazine(m1);
         txt_titre.setText("");
         txt_genre.setText("");
         txt_image.setText("");
         txt_description.setText("");
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Magazine ajouté");
         alert.show();
         
        
    }

    @FXML
    private void supp(ActionEvent event) {
        int index=-1;
        if(listCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol1.getSelectionModel().getSelectedIndex();}
            if(listCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol2.getSelectionModel().getSelectedIndex();}
                if(listCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol3.getSelectionModel().getSelectedIndex();}
                    if(listCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol4.getSelectionModel().getSelectedIndex();}
                    int ref=listCol5.getItems().get(index);
                     MagazineService mag= new MagazineService();
                     mag.supprimerMagazine(ref);
                     refrech();
             
    }
    
}
