package com.example.stockfx;

import com.example.stockfx.entities.Marchandise;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.MyDB;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;

import static java.lang.Float.parseFloat;

public class EditfournisseurController implements Initializable {
    @FXML
    private JFXTextArea email_four_edit;

    @FXML
    private JFXTextArea matricule_fisc_edit;

    @FXML
    private JFXTextArea nom_fournisseur_edit;

    @FXML
    private JFXTextArea num_fixfour_edit;

    @FXML
    private JFXTextArea num_telfour_edit;

    @FXML
    private JFXTextArea photo_four_edit;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    //PreparedStatement preparedStatement;
    Marchandise Marchandise = null;
    private boolean update;
    int fournisseureId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public static boolean isName(String val) {
        String name = "^[A-Za-z ]*$";
        if (val.matches(name)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isemail(String val) {
        String emailv = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (val.matches(emailv)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isphonenumber(String val) {
        String num_telval = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
        if (val.matches(num_telval)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean ismatricule(String val) {
        String num_matric = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$";
        if (val.matches(num_matric)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void savechangesfourn(MouseEvent event) {
        Connection c;
        c = MyDB.getInstance().getConnection();
        String nom = nom_fournisseur_edit.getText();
        String num_tel = num_telfour_edit.getText();
        String num_fixfour = num_fixfour_edit.getText();
        String email_four = email_four_edit.getText();
        String matricule_fisc = matricule_fisc_edit.getText();
        String photo = photo_four_edit.getText();



        //Marchandise m = new Marchandise(categorieedit.getValue(), nameedit.getText(), dateedit.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd")), Double.parseDouble(quantiteedit.getText()), parseFloat(prixuedit.getText()), parseFloat(prixtedit.getText()));

        boolean isMyNameFourValide = isName(nom_fournisseur_edit.getText());
        boolean isMYNUmTelValide=isphonenumber(num_telfour_edit.getText());
        boolean isMYNumFixValide=isphonenumber(num_fixfour_edit.getText());
        boolean isMyMatriculeValide=ismatricule(email_four_edit.getText());
        boolean isMyEmailValide=isemail(matricule_fisc_edit.getText());
        if (nom_fournisseur_edit.getText()!=null && num_telfour_edit.getText()!=null && num_fixfour_edit.getText()!=null && email_four_edit.getText()!=null &&  matricule_fisc_edit.getText()!=null && isMyNameFourValide==true && isMYNUmTelValide==true && isMYNumFixValide==true && isMyMatriculeValide==true&& isMyEmailValide==true && photo_four_edit.getText()!=null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            canceleditfourn();

        }
    }
    @FXML
    void canceleditfourn() {
        nom_fournisseur_edit.setText(null);
        num_telfour_edit.setText(null);
        num_fixfour_edit.setText(null);
        email_four_edit.setText(null);
        matricule_fisc_edit.setText(null);
        photo_four_edit.setText(null);
    }
    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `Fournisseur`( `nom_fournisseur`, `numtel_fournisseur`, `numFixe_fournisseur`, `email`, `matricule_fiscale`, `photo`) VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `Fournisseur` SET "
                    + "`nom_fournisseur`=?,"
                    + "`numtel_fournisseur`=?,"
                    + "`numFixe_fournisseur`=?,"
                    + "`email`=?,"
                    + "`matricule_fiscale`=?,"
                    + "`photo`= ? WHERE id_fournisseur  = '"+fournisseureId+"'";
        }

    }
    private void insert() {

        try {
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, nom_fournisseur_edit.getText());
            ps.setString(2, num_telfour_edit.getText());
            ps.setString(3, num_fixfour_edit.getText());
            ps.setString(4, email_four_edit.getText());
            ps.setString(5, matricule_fisc_edit.getText());
            ps.setString(6, photo_four_edit.getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    void setTextField(int id_fournisseur, String nomfour, String num_tel,String num_fix,String email,String matri_fisc,String photo_four  ) {
        fournisseureId=id_fournisseur;
        nom_fournisseur_edit.setText(nomfour);
        num_telfour_edit.setText(num_tel);
        num_fixfour_edit.setText(num_fix);
        email_four_edit.setText(email);
        matricule_fisc_edit.setText(matri_fisc);
        photo_four_edit.setText(photo_four);


    }

    void setUpdate(boolean b) {
        this.update = b;

    }





    /*
        @FXML
        void ListMarch(ActionEvent event) {
            ServiceStock sp = new ServiceStock();
            try {
                String idm = Integer.toString(sp.afficher().get(0).getid_marchandise());
                id_aff_lab.setText(idm);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    */
    /*
        @FXML
        private void AddPerson(ActionEvent event) {
            PersonneService sp= new PersonneService();
            Personne p = new Personne(lnom.getText(),lprenom.getText());

            try {
                sp.ajouterp(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Person is added successfully!");
                alert.show();
                lnom.setText("");
                lprenom.setText("");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }



        }
    */


}
