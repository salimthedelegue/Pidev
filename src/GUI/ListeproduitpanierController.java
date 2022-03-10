/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CommandeService;
import service.PanierService;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author ahmed amine
 */
public class ListeproduitpanierController implements Initializable {

    @FXML
    private VBox v1;
    @FXML
    private ScrollPane ListD;
    @FXML
    private Button id_retour;
    @FXML
    private Button id_commande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                liste();

 PanierService p=new PanierService();
 if(p.verifpaniervide(1)==false){
             id_commande.setVisible(false);

 }

    }    
    public void liste(){
        v1=new VBox();
       PanierService ps=new PanierService() ;
       List<Produit> produits=ps.afficherproduitpanier(1);
       
       for(int i=0;i<produits.size();i++){
           System.out.println(ps.afficherproduitpanier(1).get(i).getId_produit());
           int j=i;
           HBox h1=new HBox();
           File file = new File("C:\\Users\\ahmed amine\\Documents\\NetBeansProjects\\gameland2\\src\\GUI\\images\\"+produits.get(i).getImage_produit());
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
        v2.setPrefWidth(122);
        Label l1=new Label();
        Label l2=new Label();
        Label l3=new Label();
        l1.setPrefHeight(30);
        l1.setPrefWidth(122);
        l2.setPrefHeight(30);
        l2.setPrefWidth(122);
        l3.setPrefHeight(80); 
        l3.setPrefWidth(122);
        l1.setText(produits.get(i).getNom_produit());
        l2.setText(String.valueOf(produits.get(i).getPrix_produit())+" DNT");
        l3.setText(produits.get(i).getDescription_produit());
        v2.getChildren().addAll(l1,l2,l3);
        v2.setStyle("-fx-padding:10px;");
        Button supprimer=new Button();
        supprimer.setText("supprimer du panier");
        supprimer.setOnAction( new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                
                        PanierService pans=new PanierService();
                        pans.supprimerproduitpanier(1,produits.get(j).getId_produit());
                        liste();
                         PanierService p=new PanierService();

                        if(p.verifpaniervide(1)==false){
             id_commande.setVisible(false);

 }
                        
                        
                    }});
        supprimer.setPrefHeight(100);
        supprimer.setPrefWidth(150);
        h1.getChildren().addAll(iv,v2,supprimer);
        h1.setStyle("-fx-padding-top:10px;");
        v1.getChildren().addAll(h1);
       }
       ListD.setContent(v1);
    }    

    @FXML
    private void retour(ActionEvent event) {
        try {
                        Parent root=FXMLLoader.load(getClass().getResource("/GUI/Panier.fxml"));
                        Scene scene = new Scene(root);
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
    private void comfirmercommande(ActionEvent event) {
        CommandeService comm=new CommandeService();
        comm.comfirmercommande(1);
        liste();
        id_commande.setVisible(false);
    }
    



}

