/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.EvenementService;
import entities.Evenement;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author nours
 */
public class AfficherEventController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab;
    
    @FXML
    private TextField rech;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnRecuperer;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnIntAjouterEvent;
    @FXML
    private TextField nom_event;
    @FXML
    private TextField image_event;
    @FXML
    private Button btn_ajouter_event;
    @FXML
    private Button btnModifierEvent;
    @FXML
    private DatePicker date_d_event;
    @FXML
    private DatePicker date_f_event;
    @FXML
    private TextField prix_event;
    @FXML
    private TextField description_event;
    @FXML
    private TextField emplacement_event;
    @FXML
    private TextField nbr_max_event;
    @FXML
    private ListView<String> tabCol1;
    @FXML
    private ListView<String> tabCol2;
    @FXML
    private ListView<String> tabCol3;
    @FXML
    private ListView<Float> tabCol4;
    @FXML
    private ListView< Integer> tabCol5;
    @FXML
    private ListView<String> tabCol6;
    @FXML
    private ListView<String> tabCol7;
    @FXML
    private ListView<Integer> tabCol8;
    @FXML
    private Button btnAff;
    @FXML
    private Button btnSupprimerEvent;
    @FXML
    private ComboBox<?> combotri1;
    @FXML
    private Button btnTriEvent;
    @FXML
    private Button btnReccupererEvent;
    @FXML
    private TableView<?> tabMagazine;
    @FXML
    private TableColumn<?, ?> colonne_image;
    @FXML
    private TableColumn<?, ?> colonne_titre;
    @FXML
    private TableColumn<?, ?> colonne_genre;
    @FXML
    private TableColumn<?, ?> colonne_description;
int reference;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String>list=FXCollections.observableArrayList("nom_evenement","date_debut","prix");
       combotri.setItems(list);
     
       
     
         EvenementService ev= new EvenementService();
           ObservableList<Evenement> evenements= ev.afficher();
             for(Evenement e : evenements){
                 
                 tabCol1.getItems().add(e.getNom());
             }
              for(Evenement e : evenements){
                 
                 tabCol2.getItems().add(e.getDateD());
             }
                for(Evenement e : evenements){
                 
                 tabCol3.getItems().add(e.getDateF());
             }
                  for(Evenement e : evenements){
                 
                 tabCol4.getItems().add(e.getPrix());
             }
                    for(Evenement e : evenements){
                 
                 tabCol5.getItems().add(e.getNbr_participant());
             }
                      for(Evenement e : evenements){
                 
                 tabCol6.getItems().add(e.getDescription());
             }
                        for(Evenement e : evenements){
                 
                 tabCol7.getItems().add(e.getEmplacement());
             }
                          for(Evenement e : evenements){
                 
                 tabCol8.getItems().add(e.getRef());
             }
    }    



    @FXML
    private void RechercheMagazine(KeyEvent event) {
    }


    @FXML
    private void supprimerMagazine(ActionEvent event) {
    }

    @FXML
    private void recupererMagazine(ActionEvent event) {
    }

    @FXML
    private void afficherEvent(ActionEvent event) {
    }

    @FXML
    private void IntAjouterEvent(ActionEvent event) {
         tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void addEvent(ActionEvent event) {
         String nom=nom_event.getText();
        String dateD=date_d_event.getValue().toString();
        String dateF=date_f_event.getValue().toString();
        String image=image_event.getText();
        float prix=Float.parseFloat(prix_event.getText());
        String description=description_event.getText();
        String emplacement=emplacement_event.getText();
        int nbr=Integer.parseInt(nbr_max_event.getText());
         Evenement e= new Evenement(nom,dateD,dateF, prix,image ,description,emplacement,4,nbr);
         
        EvenementService ev= new EvenementService();
        ev.ajouterEvenemnt(e);
        refrech();
        tabPane.getSelectionModel().select(2);
        
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
          String nom=nom_event.getText();
        String dateD=date_d_event.getValue().toString();
        String dateF=date_f_event.getValue().toString();
        String image=image_event.getText();
        float prix=Float.parseFloat(prix_event.getText());
        String description=description_event.getText();
        String emplacement=emplacement_event.getText();
        int nbr=Integer.parseInt(nbr_max_event.getText());
         Evenement e= new Evenement(nom,dateD,dateF, prix,image ,description,emplacement,4,nbr);
         
        EvenementService ev= new EvenementService();
        ev.ModifierEvenemnt(reference, e);
        refrech();
        tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void afficherEvent1(ActionEvent event) {
        tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) {
        int index=-1;
        if(tabCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol1.getSelectionModel().getSelectedIndex();}
            if(tabCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol2.getSelectionModel().getSelectedIndex();}
                if(tabCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol3.getSelectionModel().getSelectedIndex();}
                    if(tabCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol4.getSelectionModel().getSelectedIndex();}
        if(tabCol5.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol5.getSelectionModel().getSelectedIndex();} 
        if(tabCol6.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol6.getSelectionModel().getSelectedIndex();}
        if(tabCol7.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol7.getSelectionModel().getSelectedIndex();}
        
                    int ref=tabCol8.getItems().get(index);
                    System.out.println(ref);
                     EvenementService ev= new EvenementService();
                     ev.supprimerEvenemnt(ref);
                     refrech();
    }
    public void refrech(){
         tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabCol5.getItems().clear();
        tabCol6.getItems().clear();
        tabCol7.getItems().clear();
        tabCol8.getItems().clear();
        EvenementService ev= new EvenementService();
           ObservableList<Evenement> evenements= ev.afficher();
             for(Evenement e : evenements){
                 
                 tabCol1.getItems().add(e.getNom());
             }
              for(Evenement e : evenements){
                 
                 tabCol2.getItems().add(e.getDateD());
             }
                for(Evenement e : evenements){
                 
                 tabCol3.getItems().add(e.getDateF());
             }
                  for(Evenement e : evenements){
                 
                 tabCol4.getItems().add(e.getPrix());
             }
                    for(Evenement e : evenements){
                 
                 tabCol5.getItems().add(e.getNbr_participant());
             }
                      for(Evenement e : evenements){
                 
                 tabCol6.getItems().add(e.getDescription());
             }
                        for(Evenement e : evenements){
                 
                 tabCol7.getItems().add(e.getEmplacement());
             }
                          for(Evenement e : evenements){
                 
                 tabCol8.getItems().add(e.getRef());
             }
    
    }

    @FXML
    private void RechercheEvent(KeyEvent event) {
        tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabCol5.getItems().clear();
        tabCol6.getItems().clear();
        tabCol7.getItems().clear();
         tabCol8.getItems().clear();
          EvenementService ev= new EvenementService();
           ObservableList<Evenement> evenements= ev.RechercherEvenement(combotri.getSelectionModel().getSelectedItem(), rech.getText());
            for(Evenement e : evenements){
                 
                 tabCol1.getItems().add(e.getNom());
             }
            for(Evenement e : evenements){
                 
                 tabCol2.getItems().add(e.getDateD());
             }
                for(Evenement e : evenements){
                 
                 tabCol3.getItems().add(e.getDateF());
             }
                  for(Evenement e : evenements){
                 
                 tabCol4.getItems().add(e.getPrix());
             }
                    for(Evenement e : evenements){
                 
                 tabCol5.getItems().add(e.getNbr_participant());
             }
                      for(Evenement e : evenements){
                 
                 tabCol6.getItems().add(e.getDescription());
             }
                        for(Evenement e : evenements){
                 
                 tabCol7.getItems().add(e.getEmplacement());
             }
                          for(Evenement e : evenements){
                 
                 tabCol8.getItems().add(e.getRef());
             }

    }

    @FXML
    private void TrierEvent(ActionEvent event) {
        tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabCol5.getItems().clear();
        tabCol6.getItems().clear();
        tabCol7.getItems().clear();
         tabCol8.getItems().clear();
         EvenementService ev= new EvenementService();
           ObservableList<Evenement> evenements= ev.TrierEvenement(combotri.getSelectionModel().getSelectedItem());
            for(Evenement e : evenements){
                 
                 tabCol1.getItems().add(e.getNom());
             }
            for(Evenement e : evenements){
                 
                 tabCol2.getItems().add(e.getDateD());
             }
                for(Evenement e : evenements){
                 
                 tabCol3.getItems().add(e.getDateF());
             }
                  for(Evenement e : evenements){
                 
                 tabCol4.getItems().add(e.getPrix());
             }
                    for(Evenement e : evenements){
                 
                 tabCol5.getItems().add(e.getNbr_participant());
             }
                      for(Evenement e : evenements){
                 
                 tabCol6.getItems().add(e.getDescription());
             }
                        for(Evenement e : evenements){
                 
                 tabCol7.getItems().add(e.getEmplacement());
             }
                          for(Evenement e : evenements){
                 
                 tabCol8.getItems().add(e.getRef());
             }
    }

    @FXML
    private void ReccupererEvent(ActionEvent event) throws ParseException {
        int index=-1;
        if(tabCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol1.getSelectionModel().getSelectedIndex();}
            if(tabCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol2.getSelectionModel().getSelectedIndex();}
                if(tabCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol3.getSelectionModel().getSelectedIndex();}
                    if(tabCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol4.getSelectionModel().getSelectedIndex();}
        if(tabCol5.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol5.getSelectionModel().getSelectedIndex();} 
        if(tabCol6.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol6.getSelectionModel().getSelectedIndex();}
        if(tabCol7.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol7.getSelectionModel().getSelectedIndex();}
        
                     reference=tabCol8.getItems().get(index);
                    System.out.println(reference);
                     EvenementService ev= new EvenementService();
                    Evenement e= ev.RecupererEvenement(reference);
                    
         nom_event.setText(e.getNom());
          
      
         image_event.setText(e.getImage());
         prix_event.setText(Float.toString(e.getPrix()));
         description_event.setText(e.getDescription());
         emplacement_event.setText(e.getEmplacement());
         nbr_max_event.setText(Integer.toString(e.getNbr_participant()));

         
          tabPane.getSelectionModel().select(1);
          refrech();
    }
    
}
