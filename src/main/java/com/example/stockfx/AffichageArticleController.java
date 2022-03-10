/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.stockfx.entities.Article;
import com.example.stockfx.entities.Magazine;
import com.example.stockfx.services.ArticleService;
import com.example.stockfx.services.MagazineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class AffichageArticleController implements Initializable {

    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnIntAjouter;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TabPane tabPane;
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
    private Button btnSupprimerArt;
    @FXML
    private TextField rech1;
    @FXML
    private ComboBox<String> combotri1;
    @FXML
    private Button bntTri1;
    @FXML
    private Button btnMo;
    @FXML
    private TextField titre_article;
    @FXML
    private TextField genre_article;
    @FXML
    private TextField auteur_magazine;
    @FXML
    private TextField contenu_magazine;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btnModifier;
    @FXML
    private DatePicker dateAr;
    @FXML
    private ComboBox<Integer> comboOrg;
int reference;
    /**
     * Initializes the controller class.
     */
     public void refrech()
    {
          
       listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          ArticleService art= new ArticleService();
           ObservableList<Article> artciles = art.afficher();
           System.out.println(artciles);
             for(Article ar : artciles){
                 listCol1.getItems().add(ar.getTitre_article());
             }
              for(Article ar : artciles){
                 listCol2.getItems().add(ar.getGenre_article());
             }
               for(Article ar : artciles){
                 listCol3.getItems().add(ar.getAuteur_article());
             }
                for(Article ar : artciles){
                 listCol4.getItems().add(ar.getContenu_article());
             }
                for(Article ar : artciles){
                 listCol5.getItems().add(ar.getRef_article());
             }
                MagazineService maga=new MagazineService();
                comboOrg.getItems().clear();
        for(Magazine m:maga.afficher())
            comboOrg.getItems().add(m.getRef());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String>list=FXCollections.observableArrayList("Titre_article","Genre_article","Auteur_article");
       combotri1.setItems(list);
        refrech();
    }    

    @FXML
    private void AffichageMagazines(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void IntAjouter(ActionEvent event) {
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void Deconnecter(ActionEvent event) {
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
                     ArticleService ar= new ArticleService();
                     ar.supprimerArticle(ref);
                     refrech();
    }

    @FXML
    private void RechercheArticle(KeyEvent event) {
         listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          ArticleService art= new ArticleService();
           ObservableList<Article> artciles = art.afficherRech(combotri1.getSelectionModel().getSelectedItem(), rech1.getText());
           System.out.println(artciles);
             for(Article ar : artciles){
                 listCol1.getItems().add(ar.getTitre_article());
             }
              for(Article ar : artciles){
                 listCol2.getItems().add(ar.getGenre_article());
             }
               for(Article ar : artciles){
                 listCol3.getItems().add(ar.getAuteur_article());
             }
                for(Article ar : artciles){
                 listCol4.getItems().add(ar.getContenu_article());
             }
                for(Article ar : artciles){
                 listCol5.getItems().add(ar.getRef_article());
             }
                MagazineService maga=new MagazineService();
                comboOrg.getItems().clear();
        for(Magazine m:maga.afficher())
            comboOrg.getItems().add(m.getRef());

    }

    @FXML
    private void TrierArticles(ActionEvent event) {
          listCol1.getItems().clear();
    listCol2.getItems().clear();
     listCol3.getItems().clear();
      listCol4.getItems().clear();
       listCol5.getItems().clear();
          ArticleService art= new ArticleService();
           ObservableList<Article> artciles = art.afficherTri(combotri1.getSelectionModel().getSelectedItem());
           System.out.println(artciles);
             for(Article ar : artciles){
                 listCol1.getItems().add(ar.getTitre_article());
             }
              for(Article ar : artciles){
                 listCol2.getItems().add(ar.getGenre_article());
             }
               for(Article ar : artciles){
                 listCol3.getItems().add(ar.getAuteur_article());
             }
                for(Article ar : artciles){
                 listCol4.getItems().add(ar.getContenu_article());
             }
                for(Article ar : artciles){
                 listCol5.getItems().add(ar.getRef_article());
             }
                MagazineService maga=new MagazineService();
                comboOrg.getItems().clear();
        for(Magazine m:maga.afficher())
            comboOrg.getItems().add(m.getRef());


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
         ArticleService ar=new ArticleService();
        Article a=ar.returnArt(reference);
         titre_article.setText(a.getTitre_article());
         genre_article.setText(a.getGenre_article());
         auteur_magazine.setText(a.getAuteur_article());
         contenu_magazine.setText(a.getContenu_article());
         comboOrg.getSelectionModel().select(15);
          tabPane.getSelectionModel().select(1);
          refrech();
    }

    @FXML
    private void addArticle(ActionEvent event) {
         String titre=titre_article.getText();
        String genre=genre_article.getText();
        String auteur=auteur_magazine.getText();
        String contenu=contenu_magazine.getText();
        String date=dateAr.getAccessibleText();
        int org=comboOrg.getSelectionModel().getSelectedItem();
        
    titre_article.setText("");
        genre_article.setText("");
       auteur_magazine.setText("");
       contenu_magazine.setText("");
     
        Article arti=new Article(titre,contenu,auteur,genre,date,org);
       ArticleService ar= new ArticleService();
         ar.ajouterArticle(arti);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Article ajouté");
         alert.show();
         refrech();
         tabPane.getSelectionModel().select(0);
    }

    @FXML
    private void modifierArticle(ActionEvent event) {
          String titre=titre_article.getText();
        String genre=genre_article.getText();
        String auteur=auteur_magazine.getText();
        String contenu=contenu_magazine.getText();
        String date=dateAr.getAccessibleText();
        int org=comboOrg.getSelectionModel().getSelectedItem();
        
    titre_article.setText("");
        genre_article.setText("");
       auteur_magazine.setText("");
       contenu_magazine.setText("");
     
        Article arti=new Article(titre,contenu,auteur,genre,date,org);
       ArticleService ar= new ArticleService();
         ar.modifierArticle(reference, arti);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information");
         alert.setHeaderText(null);
         alert.setContentText("Article modifié");
         alert.show();
         refrech();
         tabPane.getSelectionModel().select(0);
    }
    
}
