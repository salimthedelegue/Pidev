/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import com.example.stockfx.entities.Evenement;
import com.example.stockfx.entities.Organisateur;
import com.example.stockfx.services.EvenementService;
import com.example.stockfx.services.OrganisateurService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author nours
 */
public class AfficherEventController implements Initializable {
    public static final String ACCOUNT_SID = "AC73290469ad5f265fbb6eb3496b96fe3f";
    public static final String AUTH_TOKEN = "65656466c82dcd3e2dc52deb5383377f";
    @FXML
    private TabPane tabPane;
    
    @FXML
    private TextField rech;
    @FXML
    private ComboBox<String> combotri;
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
    private Button btnTriEvent;
    @FXML
    private Button btnReccupererEvent;
int reference;
    @FXML
    private Button img_button;
    @FXML
    private ImageView img_view;
    @FXML
    private ComboBox<Integer> combo_org;
    private Parent parent;
    private  Stage stage;
    private Parent root;
    private  Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        OrganisateurService or= new OrganisateurService();
         ObservableList<Organisateur>listIdOr=or.afficher();
         for(Organisateur o:listIdOr){
             
             combo_org.getItems().add(o.getId_organisateur());
         }
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
        LocalDate dateDeb = LocalDate.parse(dateD);
        LocalDate dateFin = LocalDate.parse(dateF);
        if(LocalDate.now().compareTo(dateDeb)<0 && dateFin.compareTo(dateDeb)>0&&prix>0) {
            Evenement e = new Evenement(nom, dateD, dateF, prix, image, description, emplacement, 4, nbr);

            EvenementService ev = new EvenementService();
            ev.ajouterEvenemnt(e);
            refrech();
            tabPane.getSelectionModel().select(0);
            nom_event.setText("");

            prix_event.setText("");
            description_event.setText("");
            emplacement_event.setText("");
            nbr_max_event.setText("");
        /*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+21621258318"),
                new PhoneNumber("+18656859173"),
                "L'evenement : "+e.getNom()+" a ete ajouté.... Visiter notre application.."
        ).create();

        System.out.println("message body: " + message.getBody());*/
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(" Verifiez vos données");
            alert.show();
        }
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
        int org=combo_org.getSelectionModel().getSelectedItem();
         Evenement e= new Evenement(nom,dateD,dateF, prix,image ,description,emplacement,org,nbr);
         
        EvenementService ev= new EvenementService();
        ev.ModifierEvenemnt(reference, e);
        refrech();
        tabPane.getSelectionModel().select(0);
        nom_event.setText("");

        prix_event.setText("");
        description_event.setText("");
        emplacement_event.setText("");
        nbr_max_event.setText("");
    }

    @FXML
    private void afficherEvent1(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
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
    /* void importer(MouseEvent event) {
      FileChooser fc = new FileChooser();
fc.getExtensionFilters().add(new ExtensionFilter("Image Files","Fichier JPG",".png",".jpg"));
  File f =fc.showOpenDialog(null);
  if (f != null){
      image_event.setText(f.getAbsolutePath());

     
    }}*/

    private void importer(MouseEvent event) {
        FileChooser fc = new FileChooser();
fc.getExtensionFilters().add(new ExtensionFilter("Image Files","Fichier JPG",".png",".jpg"));
  File f =fc.showOpenDialog(null);
  if (f != null){
      image_event.setText(f.getAbsolutePath());
    }
    }
    /*
    FileChooser fc = new FileChooser();
fc.getExtensionFilters().add(new ExtensionFilter("Image Files","Fichier JPG",".png",".jpg"));
  File f =fc.showOpenDialog(null);
  if (f != null){
      url.setText(f.getAbsolutePath());
      Image image = new Image (f.toURI().toString(),img.getFitHeight(),img.getFitWidth(),true,true);
      img.setImage(image)
    */

    @FXML
    private void load(ActionEvent event) throws MalformedURLException, IOException {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = chooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            image_event.setText(chooser.getSelectedFile().getName());
           /* System.out.println(chooser.getSelectedFile().getAbsolutePath());
            System.out.println(image_event.getText());*/
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            String localURL = file.toURI().toURL().toString();
            img_view.setImage(new Image(localURL));
          /*  String fromFile = image_event.getText();
            File file1 = new File(image_event.getText());*/

    }
    
}
    @FXML
    void goto_organisateur(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Organisateur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Ventes");
        stage.show();
    }

}
