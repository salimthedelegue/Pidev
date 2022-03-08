/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tn.esprit.model.reclamation;
import tn.esprit.model.servicesMaintenance;
import tn.esprit.service.ServicesM;
import tn.esprit.service.ServicesReclamation;
import tn.esprit.utils.DBcnx;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class FXML2Controller implements Initializable {

    @FXML
    private Button retourReclamation;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox  comboS;
    @FXML
    private Button AjouterS;
    @FXML
    private TextField NbremployeTxt;
    @FXML
    private Button retourReclamation1;
    @FXML
    private ComboBox<Integer> comboS2;
    ObservableList options3 = FXCollections.observableArrayList ();
    ObservableList options4 = FXCollections.observableArrayList ();
    Connection cnx;
    @FXML
    private Button SupprimerS;
    @FXML
    private Button retourReclamation11;
    @FXML
    private ComboBox comboS3;
    @FXML
    private Button modifierButton;
    @FXML
    private TextField TxtfiledModifierNbr;
    @FXML
    private ComboBox<Integer> IDcomboS;
    @FXML
    private Button retourReclamation111;
    @FXML
    private ComboBox<String> combotri2;
    @FXML
    private Label LabelBah2;
    @FXML
    private Button trier2;
    @FXML
    private TextField rech2;
      private ListData2 listdata = new ListData2();
   private ListData2 listdataRech = new ListData2();
   private ListData2 listdataTri = new ListData2();
    @FXML
    private Button retourReclamation12;
    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data>piechartdata;
    ArrayList<Integer>cell=new ArrayList<Integer>();
        ArrayList<String>name=new ArrayList<String>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ObservableList<String> list = FXCollections.observableArrayList("Lac2","Lac1","Ezzahra","boumhel","rades","bardo");
       comboS3.setItems(list);
       comboS.setItems(list);
       fillComboSupprimer();
       fillComboModidier();
        combotri2.getItems().addAll(
            "id_service",
            "departement",
            "nbr_employes"
        );
        ServicesM sm = new ServicesM();
        LabelBah2.setText(sm.afficher().toString());
        loadData();
        piechart.setData(piechartdata);
    }    
   
    @FXML
    private void retourReclamation(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("/tn/esprit/view/FXMLDocument.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
 stage.setTitle("Gestion Reclamation");
  stage.show();
    }

    @FXML
    private void OnselectComboS(ActionEvent event) {
       String s = comboS.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void AjouterService(ActionEvent event) {
        servicesMaintenance  servicesMaintenance= new servicesMaintenance();
        ServicesM sm = new ServicesM();
        
        servicesMaintenance.setDepartement(comboS.getSelectionModel().getSelectedItem().toString());
        servicesMaintenance.setNbr_employe(Integer.parseInt(NbremployeTxt.getText()));
        sm.ajouter(servicesMaintenance);
         options3.clear();
          fillComboSupprimer();
           options4.clear();
       fillComboModidier();
     
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("le service a ete ajouter");
     alert.show();
    }

    @FXML
    private void GetDep(ActionEvent event) {
         int value = comboS2.getValue();
         System.out.println(value);
    }
    
     private void fillComboSupprimer() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from servicesm";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options3.add(rs.getInt("id_service"));
                
            }
            comboS2.setItems(options3);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
     
     
        private void fillComboModidier() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from servicesm";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options4.add(rs.getInt("id_service"));
                
            }
            IDcomboS.setItems(options4);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     

    @FXML
    private void SupprimerService(ActionEvent event) {
        int value = comboS2.getValue();
     System.out.println(value);
      ServicesM sm = new ServicesM();
     sm.supprimer(value);
      options3.clear();
     fillComboSupprimer();
         
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("le service a ete supprime");
     alert.show();
    }

    @FXML
    private void OnselectComboS2(ActionEvent event) {
        String s = comboS3.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void MofdifierService(ActionEvent event) {
            int value2 = IDcomboS.getValue();
       servicesMaintenance  servicesMaintenance= new servicesMaintenance();
        ServicesM sm = new ServicesM();
        servicesMaintenance.setId_service(value2);
         servicesMaintenance.setDepartement(comboS3.getSelectionModel().getSelectedItem().toString());
         servicesMaintenance.setNbr_employe(Integer.parseInt(TxtfiledModifierNbr.getText()));
         sm.modifer(servicesMaintenance);
         options4.clear();
          fillComboModidier();
       
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete modifier");
     alert.show();
        
    }

    @FXML
    private void GetIDS(ActionEvent event) {
          int value2 = IDcomboS.getValue();
         System.out.println(value2);
        
        
    }

    @FXML
    private void trierbouton2(ActionEvent event) {
          ServicesM sm = new ServicesM();
        LabelBah2.setText(sm.trieraff(combotri2.getSelectionModel().getSelectedItem()).toString());
    }
    
    
    
    private void loadData(){
        piechartdata=FXCollections.observableArrayList();
        try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from servicesm";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                piechartdata.add(new PieChart.Data(rs.getString("departement"),rs.getInt("nbr_employes")));
                name.add(rs.getString("departement"));
                 cell.add(rs.getInt("nbr_employes"));
                
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercheService(KeyEvent event) {
             ServicesM su = new  ServicesM() ;
    
    LabelBah2.setText(su.afficherRech(combotri2.getSelectionModel().getSelectedItem(),rech2.getText()).toString());
    }
    
}
