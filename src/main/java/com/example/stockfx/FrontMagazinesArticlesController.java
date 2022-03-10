/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;


import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.stockfx.entities.Article;
import com.example.stockfx.services.ArticleService;
import com.example.stockfx.services.MagazineService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author SHS TECH
 */
public class FrontMagazinesArticlesController implements Initializable {

    @FXML
    private ImageView imageMagazine;
    @FXML
    private Label labelTitre;
    @FXML
    private Text labelContenu;
    @FXML
    private Label labelAuteur;
    @FXML
    private Label labelDate;
    @FXML
    private Rating rate;
    @FXML
    private TextField reclamerField;
    @FXML
    private Button buttonReclamer;
    @FXML
    private Button Signaler;
    @FXML
    private Label labelVues;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArticleService ar=new ArticleService();
      
        Article a=ar.getSelected();
          ar.incrementerVues(a.getRef_article());
          labelVues.setText(Integer.toString(ar.returnVues(a.getRef_article())));
        MagazineService mag=new MagazineService();
        Image image1=new Image(getClass().getResourceAsStream(mag.getSelected().getImage_magazine1()));
           imageMagazine.setImage(image1);
        labelTitre.setText(a.getTitre_article());
    labelContenu.setText(a.getContenu_article());
   labelAuteur.setText(a.getAuteur_article());
    labelDate.setText(a.getDate_article());
    rate.setRating(ar.MoyenneRate(ar.getSelected().getRef_article()));
  
  /*  File file = new File("POSTER+IMAGE+DEC.jpg");
    Image image = new Image(file.toURI().toString());
    imageMagazine.setImage(image);*/
    }    

    @FXML
    private void rating(MouseEvent event) {
         ArticleService ar=new ArticleService();
         ar.rateArticle(ar.getSelected().getRef_article(), rate.ratingProperty().intValue());
         rate.setRating(ar.MoyenneRate(ar.getSelected().getRef_article()));
    }

    @FXML
    private void signaler(ActionEvent event) throws IOException, AWTException, InterruptedException {
         reclamerField.setVisible(true);
   buttonReclamer.setVisible(true);
  
   
   
   
    }
    @FXML
    private void imprimmer(ActionEvent event) throws IOException, AWTException, InterruptedException, DocumentException {
        ArticleService ar=new ArticleService();
        final String FILE_NAME = "itext.pdf";

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open

            Paragraph p = new Paragraph();
            p.add(ar.getSelected().getTitre_article());
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add(ar.getSelected().getContenu_article()); //no alignment

            document.add(p2);





            //close
            document.close();

            System.out.println("Done");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @FXML
    private void okSignaler(ActionEvent event) throws Exception {
        reclamerField.setVisible(false);
   buttonReclamer.setVisible(false);
          ArticleService ar=new ArticleService();
          ar.signalerArticle(ar.getSelected().getRef_article(), reclamerField.getText());
           
    }
    
}
