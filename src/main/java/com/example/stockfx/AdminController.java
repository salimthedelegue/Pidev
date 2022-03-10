
package com.example.stockfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminController implements Initializable {
    private Parent parent;
    private  Stage stage;
    private Parent root;
    private  Scene scene;
    @FXML
    private Tab tab1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goto_gestion_stock(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Stock");
        stage.show();
    }
    @FXML
    void goto_getion_user(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admininterface.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion User");
        stage.show();
    }

    @FXML
    void goto_gestion_reclamation(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Reclamation");
        stage.show();
    }
    @FXML
    void goto_gestion_ventes(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Ventes");
        stage.show();

    }
    @FXML
    void goto_gestion_magazine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AffichageMagazines.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Ventes");
        stage.show();
    }
    @FXML
    void goto_gestion_events(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AfficherEvent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion Ventes");
        stage.show();
    }
    
}
