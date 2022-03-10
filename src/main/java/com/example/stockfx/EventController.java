/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.stockfx.entities.Evenement;
import com.example.stockfx.services.EvenementService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static javax.swing.Spring.width;

/**
 * FXML Controller class
 *
 * @author nours
 */
public class EventController implements Initializable {

    @FXML
    private ImageView imgEv;
    @FXML
    private Rectangle recDate;
    @FXML
    private Rectangle recNom;
    @FXML
    private Rectangle recPrix;
    @FXML
    private Label labDate;
    @FXML
    private Label labNom;
    @FXML
    private Label labPrix;
    @FXML
    private AnchorPane container;
    @FXML
    private AnchorPane bigCon;
    @FXML
    private Hyperlink link;
    @FXML
    private TextField rech;
    @FXML
    private ImageView imgrech;
    @FXML
    private Label labelev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        EvenementService ev=new EvenementService();
                ObservableList<Evenement> evenements= ev.afficher1();
                ev.DeselectAll();
                int size=1290-250;
                labelev.setLayoutX(1290/4);
        rech.setLayoutX(114);
        rech.setPrefWidth(size);
        imgrech.setLayoutX(1110);
        int posX=114;
     int posY=100;
          int a=0;
             /* <ImageView fx:id="imgEv" fitHeight="138.0" fitWidth="189.0" layoutX="62.0" layoutY="-11.0" pickOnBounds="true" preserveRatio="true">*/
           //  Image image = new Image("images/272750578_977821313144051_7040848613990936832_n.jpg");
            /* <Rectangle fx:id="recNom" arcHeight="5.0" arcWidth="5.0" fill="#010910" height="49.0" layoutX="-4.0" layoutY="100.0" stroke="BLACK" strokeType="INSIDE" width="150.0" />
       */
   
    for(Evenement e : evenements){
        a++;
        if(a==4){
            a=1;
        posX=114;
        posY+=270;
        }
       // <Label fx:id="labDate" layoutX="4.0" layoutY="49.0" prefHeight="49.0" prefWidth="90.0" text="04/mars/2022" />
            //<Label fx:id="labNom" layoutX="22.0" layoutY="176.0" prefHeight="17.0" prefWidth="107.0" text="Karim gharbi visa" textFill="WHITE" />
            //<Label fx:id="labPrix" layoutX="293.0" layoutY="176.0" text="25DT" />
         Label lab1=new Label();
         lab1.setLayoutX(4);
         lab1.setLayoutY(49);
         lab1.setPrefHeight(49);
         lab1.setPrefWidth(90);
         lab1.setText(e.getDateD());
         
         
          Label lab2=new Label();
         lab2.setLayoutX(22);
         lab2.setLayoutY(176);
         lab2.setPrefHeight(17);
         lab2.setPrefWidth(107);
         lab2.setText(e.getNom());
         Label lab3=new Label();
         lab3.setLayoutX(293);
         lab3.setLayoutY(176);
         lab3.setPrefHeight(17);
         lab3.setPrefWidth(150);
         lab3.setText(Float.toString(e.getPrix())+" DT");
         
         AnchorPane container1=new AnchorPane();
     File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        //  <AnchorPane fx:id="container" layoutX="61.0" layoutY="53.0" prefHeight="260.0" prefWidth="337.0">
         container1.setLayoutX(posX);
         container1.setLayoutY(posY);
         container1.setPrefHeight(260);
         container1.setPrefWidth(337);
         bigCon.getChildren().add(container1);
         System.out.println(e.getImage());
         Image image1=new Image(getClass().getResourceAsStream(e.getImage()));
            ImageView img=new ImageView(image1);
   /*            <ImageView fx:id="imgEv" fitHeight="101.0" fitWidth="121.0" layoutX="73.0" layoutY="-1.0" pickOnBounds="true" preserveRatio="true" visible="false">
        */
   //    <ImageView fx:id="imgEv" fitHeight="149.0" fitWidth="259.0" layoutX="79.0" layoutY="-1.0" pickOnBounds="true">
            img.setFitHeight(149);
            img.setFitWidth(259);
            img.setLayoutX(79);
            img.setLayoutY(-1);
             Image image2=new Image(getClass().getResourceAsStream("Sold-Out-Red-Icon-Transparent-PNG.png"));
            ImageView img2=new ImageView(image2);
   /*            <ImageView fx:id="imgEv" fitHeight="101.0" fitWidth="121.0" layoutX="73.0" layoutY="-1.0" pickOnBounds="true" preserveRatio="true" visible="false">
        */
   //    <ImageView fx:id="imgEv" fitHeight="149.0" fitWidth="259.0" layoutX="79.0" layoutY="-1.0" pickOnBounds="true">
            img2.setFitHeight(149);
            img2.setFitWidth(259);
            img2.setLayoutX(79);
            img2.setLayoutY(-1);
           //<Rectangle fx:id="recDate" arcHeight="5.0" arcWidth="5.0" fill="DODGERBLUE" height="149.0" layoutX="3.0" layoutY="-1.0" stroke="BLACK" strokeType="INSIDE" width="79.0" />
           // <Rectangle fx:id="recNom" arcHeight="5.0" arcWidth="5.0" fill="#010910" height="76.0" layoutX="4.0" layoutY="147.0" stroke="BLACK" strokeType="INSIDE" width="270.0" />
          //  <Rectangle fx:id="recPrix" arcHeight="5.0" arcWidth="5.0" fill="DODGERBLUE" height="76.0" layoutX="274.0" layoutY="146.0" stroke="BLACK" strokeType="INSIDE" width="66.0" />
            Rectangle rec3=new Rectangle();
              rec3.setArcHeight(0);
             rec3.setArcWidth(0);
           rec3.setHeight(149);
           rec3.setWidth(79);
           rec3.setLayoutX(3);
           rec3.setLayoutY(-1);
           rec3.setStroke(Color.rgb(230,0,115));
          rec3.setFill(Color.rgb(230,0,115));
            container1.getChildren().add(rec3);
           
   // <Rectangle fx:id="recNom" arcHeight="5.0" arcWidth="5.0" fill="#010910" height="76.0" layoutX="4.0" layoutY="147.0" stroke="BLACK" strokeType="INSIDE" width="270.0" />
         Rectangle rec1=new Rectangle();
           rec1.setArcHeight(0);
           rec1.setArcWidth(0);
           rec1.setHeight(76);
           rec1.setWidth(270);
           rec1.setLayoutX(4);
           rec1.setLayoutY(147);
           rec1.setStroke(Color.WHITE);
           rec1.setFill(Color.WHITE);
          
            container1.getChildren().add(rec1);
            /*            <Rectangle fx:id="recPrix" arcHeight="5.0" arcWidth="5.0" fill="DODGERBLUE" height="49.0" layoutX="146.0" layoutY="100.0" stroke="BLACK" strokeType="INSIDE" width="57.0" />
   */    
            // <Hyperlink fx:id="link" layoutX="62.0" layoutY="150.0" text="Hyperlink" />
             //<Hyperlink fx:id="link" layoutX="139.0" layoutY="223.0" prefHeight="38.0" prefWidth="59.0" text="Hyperlink" visible="false" />
            Hyperlink link1 = new Hyperlink();
            link1.setLayoutX(139);
            link1.setLayoutY(223);
            link1.prefHeight(38);
            link1.prefWidth(59);
            
            link1.setText("participer");
            
            link1.setId(Integer.toString(e.getRef()));
            
         link1.setOnAction(new EventHandler<ActionEvent>(){
             @Override public void handle(ActionEvent e1){              
                 ev.selectionner(e.getRef());
                 Stage stage=(Stage)link1.getScene().getWindow();
                 stage.close();
                 Stage PrimaryStage=new Stage();
                 
                 Parent root;
                 try {
                     root = FXMLLoader.load(getClass().getResource("EventDetails.FXML"));
                
                 PrimaryStage.setTitle("View stage");
                 PrimaryStage.setScene(new Scene(root,650,600));
                 PrimaryStage.show();
                  } catch (IOException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         );
         //  <Rectangle fx:id="recPrix" arcHeight="5.0" arcWidth="5.0" fill="DODGERBLUE" height="76.0" layoutX="274.0" layoutY="146.0" stroke="BLACK" strokeType="INSIDE" width="66.0" />
            Rectangle rec2=new Rectangle();
              rec2.setArcHeight(0);
             rec2.setArcWidth(0);
           rec2.setHeight(76);
           rec2.setWidth(66);
           rec2.setLayoutX(274);
           rec2.setLayoutY(146);
           
          rec2.setStroke(Color.rgb(230,0,115));
          rec2.setFill(Color.rgb(230,0,115));
            container1.getChildren().add(rec2);
            container1.getChildren().add(lab1);
            container1.getChildren().add(lab2);
            container1.getChildren().add(lab3);
             if(e.getNbr_participant()!=e.getNbr_max_participant())
            container1.getChildren().add(link1);
            container1.getChildren().add(img);
            if(e.getNbr_participant()==e.getNbr_max_participant())
            {
                container1.getChildren().add(img2);
            }
            
    posX+=350;}
   
             
    }    

    @FXML
    private void RechercheMagazine(KeyEvent event) {
    }

    @FXML
    private void rechercherfront(ActionEvent event) {
        
    }
    
}
