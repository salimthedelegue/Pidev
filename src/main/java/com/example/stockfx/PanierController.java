/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;
import com.example.stockfx.entities.Produit;

import com.example.stockfx.entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.stockfx.services.Serviceuser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.stockfx.services.CommandeService;
import com.example.stockfx.services.PanierService;
import com.example.stockfx.services.ProduitService;

/**
 * FXML Controller class
 *
 * @author ahmed amine
 */
public class PanierController implements Initializable {

    @FXML
    private ChoiceBox<String> filtre;
    @FXML
    private TextField rech;
    @FXML
    private VBox v1;
    private String ch="";
    private String f="Tous";
    private int p=0;
    @FXML
    private ScrollPane listD;
    @FXML
    private Slider slider;
    @FXML
    private TextField pmin;
    @FXML
    private TextField pmax;
    @FXML
    private Button idpanier;
    private Parent parent;
    private  Stage stage;
    private Parent root;
    private  Scene scene;

  static  Button b=new Button();
    @FXML
    private Button id_commande_comfirmer;

     private Serviceuser us=new Serviceuser();
    /**
     * Initializes the controller class.
     */
    
    public PanierController() {
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //  System.out.println(us.getConnected().getId_user());
        
        filtre.setValue("Tous");
        filtre.getItems().add("Tous");
        filtre.getItems().add("Action");
        filtre.getItems().add("Aventure");
        filtre.getItems().add("Sport");
        filtre.setOnAction(this::filtrer);
        ProduitService ps=new ProduitService() ;
        pmax.setText(String.valueOf(ps.getMax())+" DNT");
        pmax.setDisable(true);
        pmin.setDisable(true);
        slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                p=(int) slider.getValue();
                pmin.setText(String.valueOf(p)+" DNT");
                liste();
            }
        });
        slider.setMax(ps.getMax());
        liste();
        
        
    }    
    public void filtrer(ActionEvent a){
        f=filtre.getValue();
        liste();
    }
    public void liste(){
        v1=new VBox();
       ProduitService ps=new ProduitService() ;
       List<Produit> produits=ps.readAlladmin2(ch,filtre.getValue(),p);
       for(int i=0;i<produits.size();i++){
           int j=i;
           HBox h1=new HBox();
           File file = new File("C:\\Users\\Sana\\Downloads\\v2withtableview\\Stockfx\\src\\main\\java\\com\\example\\stockfx\\GUI\\images\\"+produits.get(i).getImage_produit());
           Image image = new Image(file.toURI().toString());
           ImageView iv=new ImageView(image);
           iv.setFitWidth(200);
           iv.setFitHeight(105);
           Button b=new Button();
        //b.setText("");
        //b.setPrefHeight(200);
        b.setPrefWidth(105);
        b.setId(String.valueOf(produits.get(i).getId_produit()));
        //b.setGraphic(iv);
        VBox v2=new VBox();
        v2.setPrefHeight(140);
        v2.setPrefWidth(250);
        Label l1=new Label();
        Label l2=new Label();
        Label l3=new Label();
        l1.setPrefHeight(30);
        l1.setPrefWidth(250);
        l2.setPrefHeight(30);
        l2.setPrefWidth(250);
        l3.setPrefHeight(80); 
        l3.setPrefWidth(250);
        l1.setText("Nom jeu : "+produits.get(i).getNom_produit());
        l2.setText("Prix jeu : "+String.valueOf(produits.get(i).getPrix_produit())+" DNT");
        l2.setTranslateY(10);
        l3.setText("Description jeu : "+produits.get(i).getDescription_produit());
        v2.getChildren().addAll(l1,l2,l3);
        v2.setStyle("-fx-padding:10px;");
        Button ajouter=new Button();
        PanierService pans=new PanierService();
       
             if(pans.verifproduitpanier(us.getConnected().getId_user(),produits.get(j).getId_produit())==true)
                    {
                       ajouter.setText("Produit deja au panier");
                    }
             else
             {
                 ajouter.setText("Ajouter au panier");
             }
        
        ajouter.setId("id_ajouter");
        
        ajouter.setOnAction( new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                System.out.println(us.getConnected().getId_user());
                try {
                    if(ajouter.getText().equals("Ajouter au panier"))
                    {
                    pans.ajouterlisteproduitbase(us.getConnected().getId_user(),produits.get(j));
                       ajouter.setText("Produit deja au panier");

                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }});
        ajouter.setPrefHeight(100);
        ajouter.setPrefWidth(150);
        h1.getChildren().addAll(iv,v2,ajouter);
        h1.setStyle("-fx-padding-top:10px;");
        v1.getChildren().addAll(h1);
       }
       listD.setContent(v1);
    }    

    @FXML
    private void rech(ActionEvent event) {
        ch=rech.getText();
        liste();
    }

    @FXML
    private void voirpanier(ActionEvent event) {
        
        try {
                        Parent root=FXMLLoader.load(getClass().getResource("Listeproduitpanier.fxml"));
                        Scene scene = new Scene(root,800,600);
                        // aandi interface 9dima w bech n7el interface jdida w nsaker l 9dima donc nestaamel node
                        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Forgot Password!!");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

    @FXML
    private void listercommande(ActionEvent event) {
try {
                        Parent root=FXMLLoader.load(getClass().getResource("Listecommandecomfirmer.fxml"));
                        Scene scene = new Scene(root,800,700);
                        // aandi interface 9dima w bech n7el interface jdida w nsaker l 9dima donc nestaamel node
                        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Forgot Password!!");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListeproduitpanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    @FXML
    void goto_reclamation_front(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLFRONTSalim.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Reclamation");
        stage.show();
    }
    @FXML
    void goto_evenement_front(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion evenement");
        stage.show();
    }

    @FXML
    void goto_magazine_front(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FrontMagazine.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Magazine");
        stage.show();
    }


}
        
    
    

