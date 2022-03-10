/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Produit;
import java.awt.ScrollPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author ahmed amine
 */
public class ProduitController implements Initializable {

    @FXML
    private Button boutton_ajouter;
    @FXML
    private TextField tfnom_produit;
    @FXML
    private TextField tfdescription_produit;
    @FXML
    private TextField tfimage_produit;
    @FXML
    private TextField tflien_produit;
    @FXML
    private TextField tfprix_produit;
    @FXML
    private javafx.scene.control.ScrollPane listP;
    @FXML
    private TextField tfid_produit;
    @FXML
    private Button id_annuler;
    @FXML
    private Button id_image;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorie.setValue("Action");
        categorie.getItems().add("Action");
        categorie.getItems().add("Aventure");
        categorie.getItems().add("Sport");
        afficher();
    }    

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        if(tfnom_produit.getText().trim().isEmpty()||tfdescription_produit.getText().trim().isEmpty()||tfimage_produit.getText().trim().isEmpty()||tflien_produit.getText().trim().isEmpty()||tfprix_produit.getText().trim().isEmpty())
        {
              Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("you havent typed something");
        fail.showAndWait();
            
        }
        else
        {
                        Produit p =new Produit();
            p.setNom_produit(tfnom_produit.getText());
            p.setDescription_produit(tfdescription_produit.getText());
            p.setImage_produit(tfimage_produit.getText());
            p.setLien_produit(tflien_produit.getText());
            p.setPrix_produit(Float.valueOf(tfprix_produit.getText()));
           // p.setCategorie_produit(tfcatgorie_produit.getText());
                   
        p.setCategorie_produit(categorie.getValue());
        ProduitService ps=new ProduitService();
            if(boutton_ajouter.getText().equals("ajouter produit"))
            {
                

            ps.ajouterProduit(p);
            List<String> list=ps.getMails();
            for(int i=0;i<list.size();i++)
               
           mailUtil.sendMail(list.get(i),"ahmedamine.nafti@esprit.tn" , "211JMT2235", "test", "test",null);
                //mailUtil.sendMail(list.get(i),"ilyes.bensaid@esprit.tn" , "bacmath2017", "test", "test",null);
            afficher();
                           tfid_produit.clear();
                       tfnom_produit.clear();
                        tfdescription_produit.clear();
               tfimage_produit.clear();;
               tflien_produit.clear();
                tfprix_produit.clear();
            }
            else if(boutton_ajouter.getText().equals("modifier produit"))
            {

               ps.modifier(Integer.valueOf(tfid_produit.getText()), p);
               afficher();
               tfid_produit.clear();
                       tfnom_produit.clear();
                        tfdescription_produit.clear();
               tfimage_produit.clear();;
               tflien_produit.clear();
                tfprix_produit.clear();
                boutton_ajouter.setText("ajouter produit");
            }
        }
       
            
    }

  
    private void afficher() {
        ProduitService ps= new ProduitService();
            ObservableList <Produit> items=FXCollections.observableArrayList(ps.readAlladmin());
            VBox l=new VBox();
            for(int i=0;i<items.size();i++)
            {
                int j;
                j=i;
                VBox v=new VBox();
                 if(i%2==0){
                    v.setStyle("-fx-background-color: #FFFFFF;");
                }
                 v.setPrefWidth(5000);
                Label lnom_produit=new Label();
                lnom_produit.setText("Nom : "+items.get(i).getNom_produit());
                lnom_produit.setFont(new Font("Arial", 40));
        lnom_produit.setStyle("-fx-font-weight: bolder");
        lnom_produit.setStyle("-fx-background-color:#191970");
        lnom_produit.setTextFill(Color.web("#F0F8FF"));
        lnom_produit.setTranslateX(30);
        lnom_produit.setTranslateY(7);
                Label ldescription_produit=new Label();
                      ldescription_produit.setTranslateX(300);
        ldescription_produit.setTranslateY(-55);
                ldescription_produit.setText("Description : "+items.get(i).getDescription_produit());
                
                           File file = new File("C:\\Users\\ahmed amine\\Documents\\NetBeansProjects\\gameland2\\src\\GUI\\images\\"+items.get(i).getImage_produit());
           Image image = new Image(file.toURI().toString());
           ImageView iv=new ImageView(image);
           iv.setFitWidth(250);
           iv.setFitHeight(200);
           iv.setTranslateX(30);
           iv.setTranslateY(30);
                Label llien_produit=new Label();
               llien_produit.setText("Lien : "+items.get(i).getLien_produit());
               llien_produit.setTranslateX(30);
               
        llien_produit.setTranslateY(5);
                Label lprix_produit=new Label();
                lprix_produit.setText("Prix : "+String.valueOf(items.get(i).getPrix_produit()));
                lprix_produit.setTranslateX(300);
        lprix_produit.setTranslateY(-105);
                Label lcategorie_produit=new Label();
                lcategorie_produit.setText("Categorie : "+items.get(i).getCategorie_produit());
                lcategorie_produit.setTranslateX(300);
        lcategorie_produit.setTranslateY(-150);
                Button supprimer= new Button();
                supprimer.setText("supprimer");
                supprimer.setTranslateX(130);
                supprimer.setTranslateY(25);
                supprimer.setOnAction( new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        
                        System.out.println(j);
                            
                       ProduitService ps1=new ProduitService();

                   ps1.supprimer(items.get(j).getId_produit());
                        afficher();
                       
                    }
                });
                
                Button modifier= new Button();
                modifier.setText("modifier");
                modifier.setTranslateX(20);
                modifier.setTranslateY(-7);
                     modifier.setOnAction( new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                        
                        System.out.println(items.get(j).getId_produit());
                         boutton_ajouter.setText("modifier produit");
                       tfid_produit.setText(String.valueOf(items.get(j).getId_produit()));
                       tfnom_produit.setText(items.get(j).getNom_produit());
                        tfdescription_produit.setText(items.get(j).getDescription_produit());
               tfimage_produit.setText(items.get(j).getImage_produit());
               tflien_produit.setText(items.get(j).getLien_produit());
                tfprix_produit.setText(String.valueOf(items.get(j).getPrix_produit()));
                categorie.setValue(items.get(j).getCategorie_produit());
                id_annuler.setVisible(true);
                id_annuler.setOnAction( new EventHandler<ActionEvent>(){
                    @Override public void handle(ActionEvent e){
                        
                 boutton_ajouter.setText("ajouter produit");
id_annuler.setVisible(false);
tfid_produit.clear();
                       tfnom_produit.clear();
                        tfdescription_produit.clear();
               tfimage_produit.clear();;
               tflien_produit.clear();
                tfprix_produit.clear();
                        afficher();
                       
                    }
                });


                    }
                });
        

                
                v.getChildren().addAll(lnom_produit,iv,lcategorie_produit,lprix_produit ,ldescription_produit,llien_produit,supprimer,modifier);
                l.getChildren().addAll(v);             
            }
            
            
            listP.setContent(l);
            

       
    }

    @FXML
    private void selectinage(ActionEvent event) {
        FileChooser image=new FileChooser();
        File f=image.showOpenDialog(null);
        if(f!=null)
        {
            tfimage_produit.setText(f.getName());
            String  sourcePath = f.getPath();   // source file path
        String destinationPath="C:\\Users\\ahmed amine\\Documents\\NetBeansProjects\\gameland2\\src\\GUI\\images\\";  // destination file path
        File sourceFile = new File(sourcePath);        // Creating A Source File
        File destinationFile = new File(destinationPath+sourceFile.getName());   //Creating A Destination File. Name stays the same this way, referring to getName()
        try 
        {
        Files.copy(sourceFile.toPath(), destinationFile.toPath(),REPLACE_EXISTING);  
          // Static Methods To Copy Copy source path to destination path
        } catch(Exception e)
        {
             System.out.println(e);  // printing in case of error.
        }
        }
    }

    @FXML
    private void voirstat(ActionEvent event) {
        try {
                        Parent root=FXMLLoader.load(getClass().getResource("/GUI/StatProduit.fxml"));
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
    }
    
    


