/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import com.example.stockfx.entities.Magazine;
import com.example.stockfx.entities.User;
import com.example.stockfx.services.MagazineService;
import com.example.stockfx.services.Serviceuser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class AffichageMagazinesController implements Initializable {
String   img_magazine;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnIntAjouter;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab;
    @FXML
    private TableView<Magazine> tabMagazine;
    @FXML
    private TableColumn<Magazine, String> colonne_image;
    @FXML
    private TableColumn<Magazine, String> colonne_titre;
    @FXML
    private TableColumn<Magazine, String> colonne_genre;
    @FXML
    private TableColumn<Magazine, String> colonne_description;

    @FXML
    private TextField rech;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Button buttonTri;
    @FXML
    private TextField titre_magazine;
    @FXML
    private TextField genre_magazine;
    private TextField image_magazine;
    @FXML
    private TextField description_magazine;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnRecuperer;
int reference;
    @FXML
    private Button btnModifier;
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
    private Button btnSupprimerMag;
    @FXML
    private TextField rech1;
    @FXML
    private ComboBox<String> combotri1;
    @FXML
    private Button bntTri1;
    @FXML
    private Button btnMo;
    private Parent parent;
    private  Stage stage;
    private Parent root;
    private  Scene scene;
    public static final String ACCOUNT_SID = "AC73290469ad5f265fbb6eb3496b96fe3f";
  public static final String AUTH_TOKEN = "65656466c82dcd3e2dc52deb5383377f";
    @FXML
    private Button load;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String>list=FXCollections.observableArrayList("Titre_magazine","Genre_magazine","Description_magazine");
       combotri.setItems(list);
     
       combotri1.setItems(list);
     refrech();
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
    @FXML
    private void AffichageMagazines(ActionEvent event) {
     tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void IntAjouter(ActionEvent event) {
         tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void Deconnecter(ActionEvent event) {
          tabPane.getSelectionModel().select(0);
    }

    

    @FXML
    private void RechercheMagazine(KeyEvent event) {
      /*   listdataRech=new ListData(combotri.getSelectionModel().getSelectedItem().toString(),rech.getText());
       tabMagazine.setItems(listdataRech.getMagazines());
       colonne_image.setCellValueFactory(cell -> cell.
                getValue().getImage_magazine());
       colonne_titre.setCellValueFactory(cell -> cell.
                getValue().getTitre_magazine());
       colonne_genre.setCellValueFactory(cell -> cell.
                getValue().getGenre_magazine());
       colonne_description.setCellValueFactory(cell -> cell.
                getValue().getDescription_magazine());

       */
    }

    @FXML
    private void TrierMagazines(ActionEvent event) {
        /*
        listdataTri=new ListData(combotri.getSelectionModel().getSelectedItem().toString());
       tabMagazine.setItems(listdataTri.getMagazines());
       colonne_image.setCellValueFactory(cell -> cell.
                getValue().getImage_magazine());
       colonne_titre.setCellValueFactory(cell -> cell.
                getValue().getTitre_magazine());
       colonne_genre.setCellValueFactory(cell -> cell.
                getValue().getGenre_magazine());
       colonne_description.setCellValueFactory(cell -> cell.
                getValue().getDescription_magazine());

         */
    }

    @FXML
    private void addMagazine(ActionEvent event) {
         String titre=titre_magazine.getText();
        String genre=genre_magazine.getText();
        String description=description_magazine.getText();
         Magazine m1=new Magazine(titre,genre,img_magazine,description);
         MagazineService mag= new MagazineService();
         mag.ajouterMagazine(m1);
         titre_magazine.setText("");
         genre_magazine.setText("");
         description_magazine.setText("");
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Magazine ajouté");
         alert.show();
         refrech();
         tabPane.getSelectionModel().select(0);
       /* Serviceuser su=new Serviceuser();
        for(User u:su.afficherclients()){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

Message message = Message.creator(
    new PhoneNumber("+216"+u.getNumtel_user()),
    new PhoneNumber("+18656859173"),
    "Novelle magazine a ete ajouter...! Visiter notre application.."
).create();

System.out.println("message body: " + message.getBody());}*/
    }

   
    @FXML
    private void supprimerMagazine(ActionEvent event) {
        MagazineService mag= new MagazineService();
         
     int ref=tabMagazine.getSelectionModel().getSelectedItem().getRef();
     mag.supprimerMagazine(ref);
   refrech();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Magazine supprimé");
         
         alert.show();
       
    }

    @FXML
    private void recupererMagazine(ActionEvent event) {
       reference=tabMagazine.getSelectionModel().getSelectedItem().getRef();
        tabPane.getSelectionModel().select(1);
         MagazineService mag= new MagazineService();
         Magazine m=mag.returnMagazine(reference);
         titre_magazine.setText(m.getTitre_magazine1());
         genre_magazine.setText(m.getGenre_magazine1());
         image_magazine.setText(m.getImage_magazine1());
         description_magazine.setText(m.getDescription_magazine1());
          refrech();
     
       
    }

    @FXML
    private void modifierMagazine(ActionEvent event) {
         Magazine m1=new Magazine(titre_magazine.getText(),genre_magazine.getText(),img_magazine,description_magazine.getText());
         MagazineService mag= new MagazineService();
         mag.modifierMagazine(reference, m1);
         refrech();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Magazine modifié");
         
         alert.show();
         tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void supprimerMag(ActionEvent event) {
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

    private void RechercheMagazine1(ActionEvent event) {
          
    }

    @FXML
    private void TrierMagazines1(ActionEvent event) {
      listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          MagazineService mag= new MagazineService();
           ObservableList<Magazine> magazines = mag.afficherTri(combotri1.getSelectionModel().getSelectedItem());
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

    @FXML
    private void RechercheMagazine1(KeyEvent event) {
        listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          MagazineService mag= new MagazineService();
           ObservableList<Magazine> magazines = mag.afficherRech(combotri1.getSelectionModel().getSelectedItem(), rech1.getText());
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

    @FXML
    private void recupererMagazine1(ActionEvent event) {

       
      int index=-1;
        if(listCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol1.getSelectionModel().getSelectedIndex();}
            if(listCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol2.getSelectionModel().getSelectedIndex();}
                if(listCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol3.getSelectionModel().getSelectedIndex();}
                    if(listCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=listCol4.getSelectionModel().getSelectedIndex();}
                    reference=listCol5.getItems().get(index);
         MagazineService mag= new MagazineService();
         Magazine m=mag.returnMagazine(reference);
         titre_magazine.setText(m.getTitre_magazine1());
         genre_magazine.setText(m.getGenre_magazine1());
        
         description_magazine.setText(m.getDescription_magazine1());
          tabPane.getSelectionModel().select(1);
          refrech();
    }

    @FXML
    private void load(ActionEvent event) {
       
JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = chooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            img_magazine=chooser.getSelectedFile().getName();
           /* System.out.println(chooser.getSelectedFile().getAbsolutePath());
            System.out.println(image_event.getText());*/
          
          /*  String fromFile = image_event.getText();
            File file1 = new File(image_event.getText());*/

    }
    }
    @FXML
    void goto_article(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("affichageArticle.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Ventes");
        stage.show();
    }

    
}
