/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import com.example.stockfx.entities.Commande;
import com.example.stockfx.entities.Produit;
import com.example.stockfx.services.CommandeService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.stockfx.services.Serviceuser;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import java.awt.Color;
//import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
//import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
//import com.sun.media.sound.InvalidFormatException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
/**
 * FXML Controller class
 *
 * @author ahmed amine
 */
public class ListecommandecomfirmerController implements Initializable {

    @FXML
    private VBox v1;
    @FXML
    private ScrollPane ListD;
    @FXML
    private ScrollPane ListB;
    @FXML
    private VBox vp;
    @FXML
    private Button id_facture;
    @FXML
    private Button id_retour;
    @FXML
    private TextField tfidcommande;
    //private Button down;
    @FXML
    private Button gett;
    @FXML
    private ImageView qrimage;
    @FXML
    private Label ldate;
    @FXML
    private AnchorPane anchor;

    private Serviceuser us=new Serviceuser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        liste();
    }    
    public void liste(){
        v1=new VBox();
        CommandeService comm=new CommandeService();
       List<Commande> commandes=comm.affichercommandecomfirmer(us.getConnected().getId_user());
       for(int i=0;i<commandes.size();i++){
           int j=i;
           HBox h1=new HBox();
           Button b=new Button();
        //b.setText("");
        //b.setPrefHeight(200);
        b.setPrefWidth(105);
        //b.setGraphic(iv);
        VBox v2=new VBox();
        v2.setPrefHeight(140);
        v2.setPrefWidth(300);
        Label l1=new Label();
        Label l2=new Label();
        Label l3=new Label();
        Label l4=new Label();
        l1.setPrefHeight(30);
        l1.setPrefWidth(300);
        l2.setPrefHeight(30);
        l2.setPrefWidth(122);
        l3.setPrefHeight(80); 
        l3.setPrefWidth(122);
        l4.setPrefHeight(80); 
        l4.setPrefWidth(122);
        l1.setText(String.valueOf("numero commande : " + commandes.get(i).getId_commande()));
        l2.setText(String.valueOf(commandes.get(i).getPrix_totale()));
        l3.setText("Adresse : "+commandes.get(i).getAdresse_livraison());
        l4.setText(String.valueOf(commandes.get(i).getDate_commande()));
        v2.getChildren().addAll(l1,l2,l3,l4);
        v2.setStyle("-fx-padding:10px;");
        Button afficher=new Button();
        afficher.setText("voir les produit");
        afficher.setOnAction( new EventHandler<ActionEvent>(){
            
            
            @Override 
            
            public void handle(ActionEvent e){
            id_facture.setVisible(true);
            gett.setVisible(true);
            tfidcommande.setText(String.valueOf(commandes.get(j).getId_commande()));
                        ldate.setText(String.valueOf("Commande : "+commandes.get(j).getId_commande()+" Date : "+String.valueOf(commandes.get(j).getDate_commande()+" Prix : "+String.valueOf(commandes.get(j).prix_totale))));

               vp=new VBox();
       CommandeService ps=new CommandeService();
       List<Produit> produits=ps.afficherproduitducommande(commandes.get(j).getId_commande());
       for(int k=0;k<produits.size();k++){
           int k1=k;
           HBox h11=new HBox();
           File file = new File("C:\\Users\\Sana\\Downloads\\v2withtableview\\Stockfx\\src\\main\\java\\com\\example\\stockfx\\GUI\\images\\"+produits.get(k).getImage_produit());
           Image image = new Image(file.toURI().toString());
           ImageView iv=new ImageView(image);
           iv.setFitWidth(200);
           iv.setFitHeight(105);

        VBox v22=new VBox();
        v22.setPrefHeight(80);
        v22.setPrefWidth(1000);
        Label l11=new Label();
        Label l22=new Label();
        Label l33=new Label();
        Label l44=new Label();
        l11.setPrefHeight(30);
        l11.setPrefWidth(122);
        l22.setPrefHeight(30);
        l22.setPrefWidth(122);
        l33.setPrefHeight(80); 
        l33.setPrefWidth(122);
        l44.setPrefHeight(80); 
        l44.setPrefWidth(500);
        l11.setText(produits.get(k).getNom_produit());
        l22.setText(String.valueOf(produits.get(k).getPrix_produit())+" DNT");
        l33.setText(produits.get(k).getDescription_produit());
        l44.setText("Votre lien : "+produits.get(k).getLien_produit());
        v22.getChildren().addAll(l11,l22,l33,l44);
        v22.setStyle("-fx-padding:10px;");
        
        h11.getChildren().addAll(iv,v22);
        h11.setTranslateX(30);
        h11.setTranslateY(20);
        h11.setStyle("-fx-padding-top:10px;");
        vp.getChildren().addAll(h11);
       }
       
       ListB.setContent(vp);}});
        
        
        
        
        afficher.setPrefHeight(100);
        afficher.setPrefWidth(150);
        h1.getChildren().addAll(v2);
        h1.setStyle("-fx-padding-top:10px;");
        v1.getChildren().addAll(h1,afficher);
       }
       ListD.setContent(v1);
    }    


    @FXML
    private void retour(ActionEvent event) {
         try {
                    Parent root= FXMLLoader.load(getClass().getResource("Panier.fxml"));
                    Scene scene= new Scene(root,800,600);
                     Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Forgot Password!!");
                        stage.setScene(scene);
                        stage.show();
           
           
        } catch (IOException ex) {
            Logger.getLogger(ListecommandecomfirmerController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    
/*
    private ImageView QrCode(String content) throws FileNotFoundException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = content;
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        qrView.setFitHeight(50);
        qrView.setFitWidth(50);
        
        
        return qrView;
    }



 */
//////////////////////////////
   /* public void down() throws FileNotFoundException{
        saveToFile(QrCode(ldate.getText()).getImage());
    }*/
    /*public static void saveToFile(Image image) {
    
        File outputFile = new File("C:\\Users\\ahmed amine\\Documents\\NetBeansProjects\\gameland2\\src\\GUI\\images\\qr.png");
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
      ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }*/
    @FXML
    public void qr(){
      //  qrimage.setImage(QrCode(ldate.getText()).getImage());
    //    down.setVisible(true);
       

    }

    private void imprimerr(Commande c) throws FileNotFoundException, DocumentException {
     Document doc=new Document() {};
     FileChooser fc=new FileChooser();
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File","*.pdf"));
     fc.setTitle("save contract");
     fc.setInitialFileName("untitled.pdf");
     Stage stage=(Stage) anchor.getScene().getWindow();
     File file=fc.showSaveDialog(stage);
     if(file!=null){
         String str=file.getAbsolutePath();
        try {Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new CMYKColor(255, 255, 255, 255));
            PdfWriter.getInstance(doc, new FileOutputStream(str));
            doc.open();
            Paragraph p=new Paragraph("                                                         Facture ",blueFont);
            doc.add(p);
            
            Paragraph contenu = new Paragraph(preparerMsg(c), blackFont);
            doc.add(contenu);
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Commande.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    private String preparerMsg(Commande c) {
    String ch; 
            ch="\n \n \n  Commande :\n \n"+c.getId_commande()
            + "\n Prix :\n \n"+c.getPrix_totale()
            + "\n Date : "+c.getDate_commande()+"\n \n \n"
                    +"Produits : \n \n \n";
            List<Produit> lproduits =new ArrayList<Produit>();
            CommandeService cs=new CommandeService();
            lproduits=cs.afficherproduitducommande(c.getId_commande());
            
            for(Produit p:lproduits)
            {
                ch=ch+"nom :   "+p.getNom_produit()+" \n "
                        +"Description :   "+p.getDescription_produit()+" \n "
                        +"Prix :   "+p.getPrix_produit()+" \n "
                        +"Categorie :   "+p.getCategorie_produit()+" \n "
                        +"lien :   "+p.getLien_produit()+" \n \n \n";
            }
            
    return ch;
    }

    @FXML
    private void imprimer(ActionEvent event) throws FileNotFoundException, DocumentException {
        Commande c;
        CommandeService cs=new CommandeService();

        c=cs.affichercommande(Integer.valueOf(tfidcommande.getText()));
                        System.out.println(c.getId_commande());

        imprimerr(c);
        System.out.println("ok");
    }
    
    
}
