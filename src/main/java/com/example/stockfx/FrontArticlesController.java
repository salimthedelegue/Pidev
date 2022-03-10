/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.awt.Paint;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.stockfx.entities.Article;
import com.example.stockfx.services.ArticleService;
import com.example.stockfx.services.MagazineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class FrontArticlesController implements Initializable {

    @FXML
    private AnchorPane bigCon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArticleService ar=new ArticleService();
        ar.deselectionner();
        MagazineService mag=new MagazineService();
        
        ObservableList<Article> articles =ar.getArticlesMag(mag.getSelected().getRef());
        int posX=40;
        int posY=35;
        int a=0;
        
       for(Article art : articles){
           a++;
           if(a==4){
           a=1;
           posX=40;
           posY+=160;
           }
        AnchorPane container=new AnchorPane();
        container.setLayoutX(posX);
        container.setLayoutY(posY);
        container.setPrefSize(133, 121);
        
        Rectangle rec=new Rectangle();
        rec.setArcHeight(5);
       rec.setArcWidth(5);
       rec.setHeight(121);
       rec.setWidth(135);
        Label lab2=new Label();
        lab2.setLayoutX(23);
        lab2.setLayoutY(11);
        lab2.setPrefHeight(17);
        lab2.setPrefWidth(77);
        lab2.setText(art.getTitre_article());
            Label lab1=new Label();
        lab1.setLayoutX(23);
        lab1.setLayoutY(35);
        lab1.setPrefHeight(17);
        lab1.setPrefWidth(77);
        lab1.setText(art.getGenre_article());
           Label lab3=new Label();
        lab3.setLayoutX(23);
        lab3.setLayoutY(61);
        lab3.setPrefHeight(17);
        lab3.setPrefWidth(77);
        lab3.setText(art.getAuteur_article());
        Hyperlink link=new Hyperlink();
        link.setLayoutX(40);
        link.setLayoutY(86);
        link.setPrefSize(63, 23);
        link.setText("Voir");
         link.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e1){
                   ar.selectionner(art.getRef_article());
                   Stage stage=(Stage)link.getScene().getWindow();
                     stage.close();
                     Stage PrimaryStage=new Stage();
                     Parent root;
                     try{
                         root=FXMLLoader.load(getClass().getResource("FrontMagazinesArticles.fxml"));
                         PrimaryStage.setTitle("New stage");
                         PrimaryStage.setScene(new Scene(root,650,600));
                         PrimaryStage.show();
                                 
                                 
                     }
                     catch(IOException ex){
                         System.out.println("erreur"); 
                     }
                } });
        bigCon.getChildren().add(container);
        container.getChildren().add(rec);
        container.getChildren().add(lab1);
        container.getChildren().add(lab2);
        container.getChildren().add(lab3);
        container.getChildren().add(link);
         posX+=180;}
    }
        
    
}
