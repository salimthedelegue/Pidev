package com.example.stockfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

//import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.example.stockfx.entities.Marchandise;
import com.example.stockfx.services.ServiceStock;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.MyDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class HelloController implements Initializable {
    //@FXML
    //private CategoryAxis CategoryAxis;
    @FXML
    private BarChart<Float, String> barChar;
    @FXML
    private Label cat_aff_lab;

    @FXML
    private Label date_aff_lab;

    @FXML
    private Label id_aff_lab;

    @FXML
    private Label idf_aff_lab;

    @FXML
    private Label nom_aff_lab;

    @FXML
    private Label prt_aff_lab;

    @FXML
    private Label pru_aff_lab;

    @FXML
    private Label qnt_aff_lab;

    @FXML
    private JFXTextArea Innom;

    @FXML
    private JFXTextArea Inprixtotal;

    @FXML
    private Tab btn;

    @FXML
    private Tab btn1;

    @FXML
    private ComboBox<String> incategorie;

    @FXML
    private DatePicker indate;

    @FXML
    private JFXTextArea inidfournisseur;

    @FXML
    private JFXTextArea inprixunitaire;

    @FXML
    private JFXTextArea inqunatite;
    @FXML
    private ListView<?> Listview;
    @FXML
    private TableColumn<Marchandise, String> categoriecol;

    @FXML
    private TableColumn<Marchandise, String> datecol;

    @FXML
    private TableColumn<Marchandise, Integer> idCol;

    @FXML
    private TableColumn<Marchandise, Integer> idfournisseurcol;
    @FXML
    private TableColumn<Marchandise, String> nomcol;

    @FXML
    private TableColumn<Marchandise, Float> prixtotcol;

    @FXML
    private TableColumn<Marchandise, Float> prixucol;

    @FXML
    private TableColumn<Marchandise, Float> quantcol;
    @FXML
    private TableView<Marchandise> tablemarchandise;
    @FXML
    private TableColumn<Marchandise, String> Editcol;
    Marchandise marchandise = null ;


    @FXML
    private Tab tab1;
    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("pick one", "PS DVD", "Hardware components", "Gears");
    private ObservableList<ObservableList> data;
    private ObservableList<Marchandise> oblist = FXCollections.observableArrayList();
    private ObservableList<String> marchNames = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        incategorie.setItems(dbTypeList);
        loadDate();

    }

    private void loadDate() {
        Connection c;
        c = MyDB.getInstance().getConnection();
        Refrechtable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_marchandise"));
        categoriecol.setCellValueFactory(new PropertyValueFactory<>("categorie_marchandise"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_marchandise"));
        quantcol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date_arrive"));
        prixucol.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire_marchandise"));
        prixtotcol.setCellValueFactory(new PropertyValueFactory<>("prix_total_marchandise"));
        idfournisseurcol.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));

        //add cell of button edit
        Callback<TableColumn<Marchandise, String>, TableCell<Marchandise, String>> cellFoctory = (TableColumn<Marchandise, String> param) -> {
            // make cell containing buttons
            final TableCell<Marchandise, String> cell = new TableCell<Marchandise, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    Connection c;
                    super.updateItem(item, empty);

                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(

                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                         editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                Connection cnx;
                                cnx = MyDB.getInstance().getConnection();
                                marchandise = tablemarchandise.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `Marchandise` WHERE id_marchandise  ="+marchandise.getId_marchandise();
                                PreparedStatement ps = cnx.prepareStatement(query);
                                ps.execute();
                                Refrechtable();

                            } catch (SQLException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            marchandise = tablemarchandise.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Editmarch.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            EditController editController = loader.getController();
                            editController.setUpdate(true);
                            editController.setTextField(marchandise.getId_marchandise(), marchandise.getNom_marchandise(),marchandise.getCategorie_marchandise(),
                                    marchandise.getDate_arrive(),marchandise.getQuantite(),marchandise.getPrix_unitaire_marchandise(),marchandise.getPrix_total_marchandise());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }

                }

            };

            return cell;
        };
        Editcol.setCellFactory(cellFoctory);
        tablemarchandise.setItems(oblist);


    }



    @FXML
    private void AddMarch(ActionEvent event) {
        ServiceStock sp = new ServiceStock();
        Marchandise m = new Marchandise(incategorie.getValue(), Innom.getText(), indate.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd")), Double.parseDouble(inqunatite.getText()), Float.parseFloat(inprixunitaire.getText()), Float.parseFloat(Inprixtotal.getText()), Integer.parseInt(inidfournisseur.getText()));
        try {
            sp.ajouter(m);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("added successfully!");
            alert.show();
            //incategorie.set(0);
            Innom.setText("");
            indate.setValue(null);
            inqunatite.setText("");
            inprixunitaire.setText("");
            Inprixtotal.setText("");
            inidfournisseur.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
    @FXML
    void Refrechtable() {
        Connection c;

        try {
            c = MyDB.getInstance().getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from MARCHANDISE";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()){
                oblist.add(new Marchandise(rs.getInt("id_marchandise"),rs.getString("categorie_marchandise"),rs.getString("nom_marchandise"),rs.getString("date_arrive"),rs.getFloat("quantite"),rs.getFloat("prix_unitaire_marchandise"),rs.getFloat("prix_total_marchandise"),rs.getInt("Id_fournisseur")));

            }


        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void ListMarch(ActionEvent event) {
        Connection c;

        try {
            c = MyDB.getInstance().getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from MARCHANDISE";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
        while (rs.next()){
            oblist.add(new Marchandise(rs.getInt("id_marchandise"),rs.getString("categorie_marchandise"),rs.getString("nom_marchandise"),rs.getString("date_arrive"),rs.getFloat("quantite"),rs.getFloat("prix_unitaire_marchandise"),rs.getFloat("prix_total_marchandise"),rs.getInt("Id_fournisseur")));

        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*
    public void statMarchData(List<Marchandise> march) throws SQLException {
        ServiceStock sp = new ServiceStock();
        sp.afficher();
        Float[] qunts =sp.afficher().;
        // Convert it to a list and add it to our ObservableList of months.
        marchNames.addAll(Arrays.asList(qunts));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        // Count the number of people having their birthday in a specific month.
        int[] quantCounter = new int[12];
        for (Marchandise m : march) {
            int month = sp.afficher(). - 1;
            quantCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < quantCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(marchNames.get(i), quantCounter[i]));
        }

        barChar.getData().add(series);
    }

     */
}
