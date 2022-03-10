/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.stockfx.entities.Organisateur;
import com.example.stockfx.services.OrganisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class OrganisateurController implements Initializable {

    @FXML
    private Button btnhomeO;
    @FXML
    private Button btnIntAjouterOrg;
    @FXML
    private Button btnAffOrg;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab;
    @FXML
    private TextField nom_org;
    @FXML
    private ComboBox<String> type_org;
    @FXML
    private Button btn_ajouter_org;
    @FXML
    private Button btnModifierOrg;
    @FXML
    private TextField email_org;
    @FXML
    private TextField num_org;
    @FXML
    private ListView<String> tabCol1;
    @FXML
    private ListView<String> tabCol2;
    @FXML
    private ListView<String> tabCol3;
    @FXML
    private ListView<Integer> tabCol4;
    @FXML
    private TextField rech1;
    @FXML
    private ComboBox<String> combotri1;
    @FXML
    private Button btnSupprimerOrg;
    @FXML
    private Button btnReccupererOrg;
    @FXML
    private Button btnTriOrg;
    @FXML
    private ListView<Integer> tabcol11;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabPane.getSelectionModel().select(2);
          ObservableList<String>list=FXCollections.observableArrayList("nom","email_organisateur","type");
       combotri1.setItems(list);
        ObservableList<String>list1=FXCollections.observableArrayList("Societe","Club","Individu");
        type_org.setItems(list1);
        // TODO
        OrganisateurService or= new OrganisateurService();
           ObservableList<Organisateur> organisateurs= or.afficher();
             for(Organisateur o : organisateurs){
                 
                 tabCol1.getItems().add(o.getNom());
             }
               for(Organisateur o : organisateurs){
                 
                 tabCol2.getItems().add(o.getType());
             }
                  for(Organisateur o : organisateurs){
                 
                 tabCol3.getItems().add(o.getEmail());
             }
                     for(Organisateur o : organisateurs){
                 
                 tabCol4.getItems().add(o.getNumTel_organisateur());
             }
                        for(Organisateur o : organisateurs){
                 
                 tabcol11.getItems().add(o.getId_organisateur());
             }
           
    }    

    @FXML
    private void afficherOrg(ActionEvent event) {
         tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void IntAjouterOrg(ActionEvent event) {
        tabPane.getSelectionModel().select(1);
    }

    @FXML
    private void afficherOrg1(ActionEvent event) {
            tabPane.getSelectionModel().select(2);
    }

    @FXML
    private void addOrg(ActionEvent event) throws Exception {
        String nom=nom_org.getText();
     
        String type=type_org.getSelectionModel().getSelectedItem();
       
        String email=email_org.getText();
        
        int num_tel=Integer.parseInt(num_org.getText());
         Organisateur o= new Organisateur(nom,type,email, num_tel);

         
        OrganisateurService or= new OrganisateurService();
        or.ajouterOrganisateur(o);
       refrech();
        tabPane.getSelectionModel().select(2);
        nom_org.setText("");


        email_org.setText("");

        num_org.setText("");

    }

    @FXML
    private void modifierOrg(ActionEvent event) {
         String nom=nom_org.getText();

        String type=type_org.getSelectionModel().getSelectedItem();
       
        String email=email_org.getText();
        
        int num_tel=Integer.parseInt(num_org.getText());
         Organisateur org= new Organisateur(nom,type,email, num_tel);

         
        OrganisateurService or= new OrganisateurService();
        or.ModifierOrganisateur(id, org);
       refrech();
        tabPane.getSelectionModel().select(2);
        nom_org.setText("");


        email_org.setText("");

       num_org.setText("");
         
    }


    @FXML
    private void RechercheMagazine(KeyEvent event) {
    }

    @FXML
    private void SupprimerOrg(ActionEvent event) {
         int index=-1;
        if(tabCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol1.getSelectionModel().getSelectedIndex();}
            if(tabCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol2.getSelectionModel().getSelectedIndex();}
                if(tabCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol3.getSelectionModel().getSelectedIndex();}
                    if(tabCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol4.getSelectionModel().getSelectedIndex();}
                    
       
        
                    int id=tabcol11.getItems().get(index);
                    System.out.println(id);
                     OrganisateurService or= new OrganisateurService();
                     or.supprimerOrganisateur(id);
                     refrech();
    }
    public void refrech(){
         tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabcol11.getItems().clear();
        OrganisateurService or= new OrganisateurService();
           ObservableList<Organisateur> organisateurs= or.afficher();
             for(Organisateur o : organisateurs){
                 
                 tabCol1.getItems().add(o.getNom());
             }
               for(Organisateur o : organisateurs){
                 
                 tabCol2.getItems().add(o.getType());
             }
                  for(Organisateur o : organisateurs){
                 
                 tabCol3.getItems().add(o.getEmail());
             }
                     for(Organisateur o : organisateurs){
                 
                 tabCol4.getItems().add(o.getNumTel_organisateur());
             }
                        for(Organisateur o : organisateurs){
                 
                 tabcol11.getItems().add(o.getId_organisateur());
             }
    
    }

    @FXML
    private void ReccupererOrg(ActionEvent event) {
                 int index=-1;
        if(tabCol1.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol1.getSelectionModel().getSelectedIndex();}
            if(tabCol2.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol2.getSelectionModel().getSelectedIndex();}
                if(tabCol3.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol3.getSelectionModel().getSelectedIndex();}
                    if(tabCol4.getSelectionModel().getSelectedIndex()!=-1){
         index=tabCol4.getSelectionModel().getSelectedIndex();}
                    
       
        
                     id=tabcol11.getItems().get(index);
                    System.out.println(id);
                     OrganisateurService or= new OrganisateurService();
                     Organisateur o=or.RecupererOrganisateur(id);
                   
          
      
         nom_org.setText(o.getNom());
      

         email_org.setText(o.getEmail());
         num_org.setText(Integer.toString(o.getNumTel_organisateur()));

         
          tabPane.getSelectionModel().select(1);
          refrech();
    }

    @FXML
    private void TrierOrg(ActionEvent event) {
        tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabcol11.getItems().clear();
        OrganisateurService or= new OrganisateurService();
           ObservableList<Organisateur> organisateurs= or.trierOrganisateur(combotri1.getSelectionModel().getSelectedItem());
            
             for(Organisateur o : organisateurs){
                 
                 tabCol1.getItems().add(o.getNom());
             }
               for(Organisateur o : organisateurs){
                 
                 tabCol2.getItems().add(o.getType());
             }
                  for(Organisateur o : organisateurs){
                 
                 tabCol3.getItems().add(o.getEmail());
             }
                     for(Organisateur o : organisateurs){
                 
                 tabCol4.getItems().add(o.getNumTel_organisateur());
             }
                        for(Organisateur o : organisateurs){
                 
                 tabcol11.getItems().add(o.getId_organisateur());
             }
    }

    @FXML
    private void RechercheOrg(KeyEvent event) {
        tabCol1.getItems().clear();
        tabCol2.getItems().clear();
        tabCol3.getItems().clear();
        tabCol4.getItems().clear();
        tabcol11.getItems().clear();
        OrganisateurService or= new OrganisateurService();
           ObservableList<Organisateur> organisateurs= or.rechercherOrganisateur(combotri1.getSelectionModel().getSelectedItem(),rech1.getText());
            
             for(Organisateur o : organisateurs){
                 
                 tabCol1.getItems().add(o.getNom());
             }
               for(Organisateur o : organisateurs){
                 
                 tabCol2.getItems().add(o.getType());
             }
                  for(Organisateur o : organisateurs){
                 
                 tabCol3.getItems().add(o.getEmail());
             }
                     for(Organisateur o : organisateurs){
                 
                 tabCol4.getItems().add(o.getNumTel_organisateur());
             }
                        for(Organisateur o : organisateurs){
                 
                 tabcol11.getItems().add(o.getId_organisateur());
             }
    }
    
}
