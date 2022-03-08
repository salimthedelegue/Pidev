/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.model.Pdf;
import tn.esprit.model.reclamation;
import tn.esprit.model.mailing;
import tn.esprit.model.servicesMaintenance;
import tn.esprit.service.Rservices;
import tn.esprit.service.ServicesM;
import tn.esprit.service.ServicesReclamation;
import tn.esprit.utils.DBcnx;
import tn.esprit.utils.SmsApi;
import tn.esprit.view.FXML2Controller;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Tab EAjouter;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private TextArea DescriptionAjouter;
    @FXML
    private Tab ESupprimer;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private ComboBox<Integer> list;
     Connection cnx;
     ObservableList options = FXCollections.observableArrayList ();
      ObservableList options2 = FXCollections.observableArrayList ();
         ObservableList options6 = FXCollections.observableArrayList ();
    @FXML
    private Tab EModdifer;
    @FXML
    private Button ButtonModfier;
    @FXML
    private TextArea DescriptionModifier;
    @FXML
    private ComboBox<Integer> list2;
    @FXML
    private Tab EModdifer1;

    
    ServicesReclamation ps= new ServicesReclamation();
    List<reclamation> reclamations =ps.afficher();
    @FXML
    private TextField rech;
    private ListData listdata = new ListData();
   private ListData listdataRech = new ListData();
   private ListData listdataTri = new ListData();
    //@FXML
   // private TableColumn<?, ?> colonne_Date;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Label LabelBah;
    @FXML
    private Button trier;
    @FXML
    private Button GoToService;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button pdf;
    
    @FXML
    private Button GoToService1;
    @FXML
    private Button GoToService2;
    @FXML
    private ComboBox<String> list3;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillComboSupprimer();
        fillComboSupprimer2();
        combotri.getItems().addAll(
            "id_reclamation",
            "date_reclamation",
            "description_reclamation"
        );
        fillComboajoutDep();
       
        ServicesReclamation su = new ServicesReclamation() ;
    
    LabelBah.setText(su.afficher().toString());
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws Exception {
       String value = list3.getValue();
           reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setDate(LocalDateTime.now());
        reclamation.setDescription(DescriptionAjouter.getText());
//        reclamation.setIdservice();
        
      //  reclamation.setIdservice();
//      reclamation rec = sp.rechercherParDep(value);
       ServicesM service = new ServicesM();
        servicesMaintenance maint = service.rechercherParDep(value);
      reclamation.setIdservice(maint.getId_service());
        sp.ajouter(reclamation);
           options2.clear();
     fillComboSupprimer();
      options.clear();
     fillComboSupprimer2();
         mailing.sendMail("salim.ferhat@esprit.tn", "salim");
         //SmsApi.sendSMS("+21658896300","Bienvenu dans notre application");
         
         
         Notifications notificationBuilder = Notifications.create()
        .title("Information").text("le user a été modifier").graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
         
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete ajouter");
     alert.show();
    }

    @FXML
    private void Supprimer(ActionEvent event) {
          int value = list.getValue();
     System.out.println(value);
     ServicesReclamation ps = new ServicesReclamation();
     ps.supprimer(value);
     options2.clear();
     fillComboSupprimer();
      
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete supprime");
     alert.show();
    }

    @FXML
    private void Modifier(ActionEvent event) {
         int value2 = list2.getValue();
        reclamation reclamation = new reclamation();
        ServicesReclamation sp= new ServicesReclamation();
        reclamation.setId((value2));
        reclamation.setDescription(DescriptionModifier.getText());
        sp.modifer(reclamation);
         options.clear();
     fillComboSupprimer2();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la reclamation a ete modifier");
     alert.show();
    }

    @FXML
    private void GetID(ActionEvent event) {
        int value = list.getValue();
         System.out.println(value);
    }
    
    private void fillComboSupprimer() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from reclamation ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options2.add(rs.getInt("id_reclamation"));
                
            }
            list.setItems(options2);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void fillComboSupprimer2() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from reclamation ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getInt("id_reclamation"));
                
            }
            list2.setItems(options);
        } catch (SQLException ex) {
            //Logger.getLogger(tn.esprit.view.FXMLDocumentController.class.G).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GetID2(ActionEvent event) {
         int value2 = list2.getValue();
         System.out.println(value2);
    }

    @FXML
    private void ChercherAfficher(ActionEvent event) {
       ServicesReclamation su = new  ServicesReclamation() ;
    
    LabelBah.setText(su.afficherRech(combotri.getSelectionModel().getSelectedItem(),rech.getText()).toString());
    }

    @FXML
    private void RechercheTyped(KeyEvent event) {
           ServicesReclamation su = new  ServicesReclamation() ;
    
    LabelBah.setText(su.afficherRech(combotri.getSelectionModel().getSelectedItem(),rech.getText()).toString());
        
    }

    @FXML
    private void trierbouton(ActionEvent event) {
    
     ServicesReclamation su = new  ServicesReclamation() ;
    
    LabelBah.setText(su.trieraff(combotri.getSelectionModel().getSelectedItem()).toString());
    
    
    }

    @FXML
    private void GoToService(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("/tn/esprit/view/FXML2.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
 stage.setTitle("Gestion Services");
  stage.show();
    }

    @FXML
     private void PDF(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("list of users");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 

    @FXML
    private void exel(ActionEvent event) throws IOException {
       
        File file = new File("");
        
       
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        
        
        file = new File("C:\\Users\\salim\\Documents\\NetBeansProjects\\WorkshopJava3a1\\src\\tn\\esprit\\view\\reclamation.xlsx");
        if(file.exists()) desktop.open(file);
              ServicesReclamation su = new ServicesReclamation() ;
           

        su.excel();
    }

    @FXML
    private void Getdep(ActionEvent event) {
        String value = list3.getValue();
         System.out.println(value);
    }
    
    
      private void fillComboajoutDep() {
    try {
           
            cnx = DBcnx.getInstance().getCnx();
            String req = " select * from servicesm ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options6.add(rs.getString("departement"));
                
            }
            list3.setItems(options6);
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentcontroller.class.GetID()).log(Level.SEVERE, null, ex);
        }
    }
    

  


    
    
}
