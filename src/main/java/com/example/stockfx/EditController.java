package com.example.stockfx;

import com.example.stockfx.entities.Marchandise;
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

public class EditController implements Initializable {
    @FXML
    private ComboBox<String> categorieedit;

    @FXML
    private DatePicker dateedit;


    @FXML
    private TextField nameedit;

    @FXML
    private TextField prixtedit;

    @FXML
    private TextField prixuedit;

    @FXML
    private TextField quantiteedit;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    //PreparedStatement preparedStatement;
    Marchandise Marchandise = null;
    private boolean update;
    int marchandiseId;


    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("pick one", "PS DVD", "Hardware components", "Gears");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categorieedit.setItems(dbTypeList);


    }


    @FXML
    void savechanges(MouseEvent event) {
        Connection c;
        c = MyDB.getInstance().getConnection();
        String nom = nameedit.getText();
        String categorie = categorieedit.getValue();
        String datea = dateedit.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd"));
        double quant = Double.parseDouble(quantiteedit.getText());
        Float prixu=parseFloat(prixuedit.getText());
        float prixt=parseFloat(prixtedit.getText());

        //Marchandise m = new Marchandise(categorieedit.getValue(), nameedit.getText(), dateedit.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd")), Double.parseDouble(quantiteedit.getText()), parseFloat(prixuedit.getText()), parseFloat(prixtedit.getText()));


        if (nameedit.getText()==null || dateedit.getValue() == null || quantiteedit.getText()==null || prixuedit.getText()==null|| prixtedit.getText()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            cancel();

        }
    }
    @FXML
    void cancel() {
        nameedit.setText(null);
        categorieedit.setValue(null);
        dateedit.setValue(null);
        quantiteedit.setText(null);
        prixuedit.setText(null);
        prixtedit.setText(null);
    }
    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `Marchandise`( `categorie_marchandise`, `nom_marchandise`, `date_arrive`, `quantite`, `prix_unitaire_marchandise`, `prix_total_marchandise`) VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `Marchandise` SET "
                    + "`categorie_marchandise`=?,"
                    + "`nom_marchandise`=?,"
                    + "`date_arrive`=?,"
                    + "`quantite`=?,"
                    + "`prix_unitaire_marchandise`=?,"
                    + "`prix_total_marchandise`= ? WHERE id_marchandise = '"+marchandiseId+"'";
        }

    }
    private void insert() {

        try {
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, categorieedit.getValue());
            ps.setString(2, nameedit.getText());
            ps.setString(3, String.valueOf(dateedit.getValue()));
            ps.setString(4, quantiteedit.getText());
            ps.setString(5, prixuedit.getText());
            ps.setString(6, prixtedit.getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    void setTextField(int id_marchandise, String nom, String categorie,String datea,double quant,Float prixu,float prixt) {
        marchandiseId=id_marchandise;
        nameedit.setText(nom);
        categorieedit.setValue(categorie);
        //dateedit.setValue(LocalDate.parse(datea));
        quantiteedit.setText(String.valueOf(quant));
        prixuedit.setText(String.valueOf(prixu));
        prixtedit.setText(String.valueOf(prixt));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String caseStartDate = dateFormat.format(LocalDate.now());
        dateedit.setValue(LocalDate.parse(caseStartDate, dateFormat));


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
