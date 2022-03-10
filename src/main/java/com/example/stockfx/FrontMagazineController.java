/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.stockfx.entities.Magazine;
import com.example.stockfx.services.MagazineService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class FrontMagazineController implements Initializable {

    @FXML
    private AnchorPane bigCon;
 MagazineService mag= new MagazineService();
    private TextField rech;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
   
       
        mag.deselectionner();
        ObservableList<Magazine> magazines=mag.afficher();
      afficher(magazines);
                
    }    
    void afficher(ObservableList<Magazine> magazines){
         int posX=240;
       int posY=60;
       int a=0;
       for(Magazine m : magazines){
           a++;
           if(a==3){
           a=1;
           posX=240;
           posY+=210;
           }
           // <AnchorPane layoutX="86.0" layoutY="100.0" prefHeight="200.0" prefWidth="428.0">
        AnchorPane container=new AnchorPane();
        container.setLayoutX(posX);
        container.setLayoutY(posY);
        container.setPrefHeight(200);
        container.setPrefWidth(428);
         bigCon.getChildren().add(container);
          Image image = new Image(getClass().getResourceAsStream(m.getImage_magazine1()));
          
              Rectangle rec=new Rectangle();
          
              rec.setArcHeight(5);
              rec.setArcWidth(5);
              rec.setHeight(200);
              rec.setWidth(428);
              container.getChildren().add(rec);
        ImageView imageMag=new ImageView(image);
        imageMag.setFitHeight(200);
        imageMag.setFitWidth(200);
        container.getChildren().add(imageMag);
// <ScrollBar orientation="VERTICAL" prefHeight="200.0" prefWidth="14.0" />

        Label lab1=new Label();
        lab1.setLayoutX(224);
        lab1.setLayoutY(14);
        lab1.setPrefHeight(17);
        lab1.setPrefWidth(178);
        lab1.setText(m.getTitre_magazine1());
         container.getChildren().add(lab1);
           
         Label lab2=new Label();
        lab2.setLayoutX(224);
        lab2.setLayoutY(60);
        lab2.setPrefHeight(17);
        lab2.setPrefWidth(178);
          lab2.setText(m.getGenre_magazine1());
            container.getChildren().add(lab2);
            
          Text description=new Text();
          description.setLayoutX(224);
          description.setLayoutY(108);
          description.setStrokeWidth(0);
          description.setWrappingWidth(185);
          description.setText(m.getDescription_magazine1());
               
             
              
                container.getChildren().add(description);
              //  <Button layoutX="81.0" layoutY="294.0" mnemonicParsing="false" text="Voir" />
            
            Button btn=new Button();
            btn.setLayoutX(357);
            btn.setLayoutY(155);
            btn.setText("VOIR");
         btn.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e1){
                   mag.selectionner(m.getRef());
                   Stage stage=(Stage)btn.getScene().getWindow();
                     stage.close();
                     Stage PrimaryStage=new Stage();
                     Parent root;
                     try{
                         root=FXMLLoader.load(getClass().getResource("FrontArticles.fxml"));
                         PrimaryStage.setTitle("New stage");
                         PrimaryStage.setScene(new Scene(root,650,600));
                         PrimaryStage.show();
                                 
                                 
                     }
                     catch(IOException ex){
                         System.out.println("erreur"); 
                     }
                } });
             container.getChildren().add(btn);
       posX+=450;}
            
    }

    private void rechercher(KeyEvent event) {
        
        ObservableList<Magazine> magazines=mag.afficherRech("titre_article", rech.getText());
      afficher(magazines);
    }
    
}
