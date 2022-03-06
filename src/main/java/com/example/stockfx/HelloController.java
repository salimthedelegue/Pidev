package com.example.stockfx;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
//import helpers.DbConnect;
import java.awt.*;
import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.io.FileOutputStream;
import java.io.*;
import java.io.FileNotFoundException;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.util.*;
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
import javafx.scene.image.Image;
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
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

import static jdk.dynalink.linker.support.Guards.isNotNull;
//part fournisseur
import javafx.scene.image.ImageView;
import java.net.MalformedURLException;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class HelloController implements Initializable {
    @FXML
    private ImageView img_preview;
    @FXML
    private JFXTextArea text_path;

    @FXML
    private CategoryAxis CategoryAxisx;

    @FXML
    private NumberAxis CategoryAxisy;
    @FXML
    private BarChart<?, ?> barChart;
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
    Marchandise marchandise = null;


    @FXML
    private Tab tab1;
    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("pick one", "PS DVD", "Hardware components", "Gears", "Streaming related");
    private ObservableList<ObservableList> data;
    private ObservableList<Marchandise> oblist = FXCollections.observableArrayList();
    private ObservableList<Marchandise> liststat = FXCollections.observableArrayList();
    private ObservableList<String> marchNames = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        incategorie.setItems(dbTypeList);
        loadDate();
        Connection c;
        //SELECT `categorie_marchandise`,SUM(`quantite`) FROM `marchandise` GROUP BY `categorie_marchandise`;
        try {
            c = MyDB.getInstance().getConnection();
            //SQL FOR SELECTING SUM of quantities and grouby category
            String SQL = "SELECT `categorie_marchandise`,SUM(`quantite`) FROM `marchandise` GROUP BY `categorie_marchandise`";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                liststat.add(new Marchandise(rs.getString("categorie_marchandise"), rs.getFloat("SUM(`quantite`)")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series set1=new XYChart.Series<>();

        for (int i = 0; i < liststat.size(); i++) {
                //System.out.println(liststat.get(i).getCategorie_marchandise());
                //System.out.println(liststat.get(i).getQuantite());
                set1.getData().add(new XYChart.Data(liststat.get(i).getCategorie_marchandise(), liststat.get(i).getQuantite()));

                set1.setName("Quantities per category");
        }
        barChart.getData().addAll(set1);


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
                                String query = "DELETE FROM `Marchandise` WHERE id_marchandise  =" + marchandise.getId_marchandise();
                                PreparedStatement ps = cnx.prepareStatement(query);
                                ps.execute();
                                Refrechtable();

                            } catch (SQLException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            marchandise = tablemarchandise.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Editmarch.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            EditController editController = loader.getController();
                            editController.setUpdate(true);
                            editController.setTextField(marchandise.getId_marchandise(), marchandise.getNom_marchandise(), marchandise.getCategorie_marchandise(),
                                    marchandise.getDate_arrive(), marchandise.getQuantite(), marchandise.getPrix_unitaire_marchandise(), marchandise.getPrix_total_marchandise());
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

    //validators
    public static boolean isName(String val) {
        String name = "^[A-Za-z ]*$";
        if (val.matches(name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDoubl(String val) {
        String doubl = "^(-?)(0|([1-9][0-9]*))(\\\\.[0-9]+)?$";
        if (val.matches(doubl)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isreal(String val) {
        String real = "^(?=.)([+-]?([0-9]*)(\\.([0-9]+))?)$";
        if (val.matches(real)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isinte(String val) {
        String inte = "(?<=^|\\s)[0-9]+(?=$|\\s)";
        if (val.matches(inte)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isdate(String val) {
        String dateval = "(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})";
        if (val.matches(dateval)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void AddMarch(ActionEvent event) {
        //boolean isMyDateValide=isdate(String.valueOf(indate.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd"))));
        boolean isMyNameValide = isName(Innom.getText());
        boolean isMyQuantityValide = isDoubl(inqunatite.getText());
        boolean isMyPriceUValide = isreal(inprixunitaire.getText());
        boolean isMyPriceTValide = isreal(Inprixtotal.getText());
        boolean isMyFournisseurValide = isinte(inidfournisseur.getText());
        if (indate.getValue()!=null && incategorie.getValue()!=null && isMyNameValide==true && isMyQuantityValide==true && isMyPriceUValide==true && isMyPriceTValide==true&& isMyFournisseurValide==true) {
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
    else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAIL");
            alert.setContentText("PLEASE VERIFY DATA ");
            alert.show();

    }

}
    @FXML
    void Generatepdf(ActionEvent event) {
        Connection c;
        List<Marchandise> list = new ArrayList<>();
        //created PDF document instance

        try {
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            String req ="select * from marchandise";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while(rs.next()){
                Marchandise p = new Marchandise();
                p.setId_marchandise(rs.getInt(1));
                p.setNom_marchandise(rs.getString("nom_marchandise"));
                p.setCategorie_marchandise(rs.getString("categorie_marchandise"));
                p.setQuantite(rs.getFloat("quantite"));
                p.setDate_arrive(rs.getString("date_arrive"));
                p.setPrix_unitaire_marchandise(rs.getFloat("prix_unitaire_marchandise"));
                p.setPrix_total_marchandise(rs.getFloat("prix_total_marchandise"));
                p.setId_fournisseur(rs.getInt("Id_fournisseur"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            Rectangle pageSize = new Rectangle(595, 842);
            pageSize.setBackgroundColor(new BaseColor(119, 51, 255));
            Document doc = new Document(pageSize);
            doc.addAuthor("Med Ali");
            doc.addCreationDate();
            doc.addCreator("azouzi");
            doc.addTitle("Marchandises");
            doc.addSubject("Suivi Marchandises");
            //generate a PDF at the specified location
            //Simple Paragraph Pdf
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            System.out.print(desktopPath.replace("\\", "/"));
            PdfWriter.getInstance(doc, new FileOutputStream(desktopPath+"\\marchandisestest.pdf"));
            //PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Sana/Downloads/marchandises1.pdf"));
            //opens the PDF
            doc.open();
            //Create cells
            Font font = new Font();
            font.setSize(10);
            font.setColor(new BaseColor(255, 255, 255));
            PdfPTable pdfPTable = new PdfPTable(8);
            pdfPTable.setWidthPercentage(110);
            
            /*
            PdfPCell cel = new PdfPCell(new Phrase("ID_MARCH", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(cel);

            cel = new PdfPCell(new Phrase("NOM_MARCH", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(cel);

            cel = new PdfPCell(new Phrase("CATEGORIE", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(cel);

            cel = new PdfPCell(new Phrase("QUANTITE", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(cel);

            cel = new PdfPCell(new Phrase("DATE D'ARRIVE", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(cel);

            cel = new PdfPCell(new Phrase("PRIX UNITAIRE", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);

            cel = new PdfPCell(new Phrase("PRIX TOTAL", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);

            cel = new PdfPCell(new Phrase("ID FOURNISSEUR", font));
            cel.setBackgroundColor(new BaseColor(76, 175, 80));
            cel.setBorder(Rectangle.NO_BORDER);
*/
            PdfPCell pdfPCell1 = new PdfPCell(new Phrase("ID_MARCH",font));
            pdfPCell1.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell2 = new PdfPCell(new Phrase("NOM_MARCH",font));
            pdfPCell2.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell3 = new PdfPCell(new Phrase("CATEGORIE",font));
            pdfPCell3.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell4 = new PdfPCell(new Phrase("QUANTITE",font));
            pdfPCell4.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell5 = new PdfPCell(new Phrase("DATE D'ARRIVE",font));
            pdfPCell5.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell6 = new PdfPCell(new Phrase("PRIX UNITAIRE",font));
            pdfPCell6.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell7 = new PdfPCell(new Phrase("PRIX TOTAL",font));
            pdfPCell7.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell7.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell8 = new PdfPCell(new Phrase("ID FOURNISSEUR",font));
            pdfPCell8.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell8.setBorder(Rectangle.NO_BORDER);

            //Add cells to table
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);
            pdfPTable.addCell(pdfPCell6);
            pdfPTable.addCell(pdfPCell7);
            pdfPTable.addCell(pdfPCell8);
            pdfPTable.setHeaderRows(1);
            Font font2 = new Font();
            font2.setColor(new BaseColor(0, 0, 0));
            font2.setSize(10);
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 != 0) {
                    pdfPCell1 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getId_marchandise()), font2));
                    pdfPCell1.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell1);
                    pdfPCell2 = new PdfPCell(new Phrase(list.get(i).getNom_marchandise(), font2));
                    pdfPCell2.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell2);
                    pdfPCell3 = new PdfPCell(new Phrase(list.get(i).getCategorie_marchandise(), font2));
                    pdfPCell3.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell3);
                    pdfPCell4 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getQuantite()), font2));
                    pdfPCell4.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell4);
                    pdfPCell5 = new PdfPCell(new Phrase(list.get(i).getDate_arrive(), font2));
                    pdfPCell5.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell5);
                    pdfPCell6 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getPrix_unitaire_marchandise()), font2));
                    pdfPCell6.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell6);
                    pdfPCell7 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getPrix_total_marchandise()), font2));
                    pdfPCell7.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell7);
                    pdfPCell8 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getId_fournisseur()), font2));
                    pdfPCell8.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell8);
                }else{
                    pdfPCell1 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getId_marchandise()), font2));
                    pdfPCell1.setBorder(Rectangle.NO_BORDER);
                    pdfPCell1.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell1);
                    pdfPCell2 = new PdfPCell(new Phrase(list.get(i).getNom_marchandise(), font2));
                    pdfPCell2.setBorder(Rectangle.NO_BORDER);
                    pdfPCell2.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell2);
                    pdfPCell3 = new PdfPCell(new Phrase(list.get(i).getCategorie_marchandise(), font2));
                    pdfPCell3.setBorder(Rectangle.NO_BORDER);
                    pdfPCell3.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell3);
                    pdfPCell4 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getQuantite()), font2));
                    pdfPCell4.setBorder(Rectangle.NO_BORDER);
                    pdfPCell4.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell4);
                    pdfPCell5 = new PdfPCell(new Phrase(list.get(i).getDate_arrive(), font2));
                    pdfPCell5.setBorder(Rectangle.NO_BORDER);
                    pdfPCell5.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell5);
                    pdfPCell6 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getPrix_unitaire_marchandise()), font2));
                    pdfPCell6.setBorder(Rectangle.NO_BORDER);
                    pdfPCell6.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell6);
                    pdfPCell7 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getPrix_total_marchandise()), font2));
                    pdfPCell7.setBorder(Rectangle.NO_BORDER);
                    pdfPCell7.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell7);
                    pdfPCell8 = new PdfPCell(new Phrase(String.valueOf(list.get(i).getId_fournisseur()), font2));
                    pdfPCell8.setBorder(Rectangle.NO_BORDER);
                    pdfPCell8.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(pdfPCell8);
                }

            }
            Font font3= new Font();
            font3.setColor(new BaseColor(255, 255, 255));
            font3.setSize(20);
            Paragraph parag=new Paragraph("Liste des marchandises",font3);

            parag.setAlignment(Element.ALIGN_CENTER);
            parag.setSpacingAfter(50f);
            doc.add(parag);
            doc.add(pdfPTable);
            doc.close();
            Desktop.getDesktop().open(new File(desktopPath+"\\marchandisestest.pdf"));

        }
            /*
            //Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Phrase("ID_MARCH",font));
            pdfPCell1.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell2 = new PdfPCell(new Phrase("NOM_MARCH",font));
            pdfPCell2.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell3 = new PdfPCell(new Phrase("CATEGORIE",font));
            pdfPCell3.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell4 = new PdfPCell(new Phrase("QUANTITE",font));
            pdfPCell4.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell5 = new PdfPCell(new Phrase("DATE D'ARRIVE",font));
            pdfPCell5.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell6 = new PdfPCell(new Phrase("PRIX UNITAIRE",font));
            pdfPCell6.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell7 = new PdfPCell(new Phrase("PRIX TOTAL",font));
            pdfPCell7.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell7.setBorder(Rectangle.NO_BORDER);
            PdfPCell pdfPCell8 = new PdfPCell(new Phrase("ID FOURNISSEUR",font));
            pdfPCell8.setBackgroundColor(new BaseColor(128, 128, 255));
            pdfPCell8.setBorder(Rectangle.NO_BORDER);

            //Add cells to table
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);
            pdfPTable.addCell(pdfPCell6);
            pdfPTable.addCell(pdfPCell7);
            pdfPTable.addCell(pdfPCell8);
            Font font2 = new Font();
            font2.setColor(new BaseColor(0, 0, 0));
            font2.setSize(10);
            for (int i = 0; i < list.size(); i++) {
                if (i % 2 != 0) {
                    pdfPCell1 = new PdfPCell(new Phrase(list.get(i).getId_marchandise(),font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(list.get(i).getPrenom(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getSexe(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(c);
                }else{
                    c = new PdfPCell(new Phrase(List.get(i).getNom(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getPrenom(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getSexe(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    pdfPTable.addCell(c);
                }

            }
            Paragraph parag=new Paragraph("Liste des Freelancers");
            parag.setAlignment(Element.ALIGN_CENTER);
            parag.setSpacingAfter(50f);
            doc.add(parag);

            //Add content to the document using Table objects.
            doc.add(pdfPTable);

            //close the PDF file
            doc.close();
            //closes the writer
            writer.close();
            */
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void statMarchData(List<Marchandise> march) throws SQLException {
        //Defining string to label XAxis
        String Euro = "Euro";
        String Pound = "British Pound";
        String A_Dollar = "Austrelian Dollar";
        String frenc= "Swis Franc";
        CategoryAxis xaxis= new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0.1,2,0.1);
        xaxis.setLabel("Currency");
        yaxis.setLabel("Dollar price");
        //Configuring BarChart
        BarChart<String,Float> bar = new BarChart(xaxis,yaxis);
        bar.setTitle("Dollar Conversion chart");

        //Configuring Series for XY chart
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data(Euro,0.83));
        series.getData().add(new XYChart.Data(Pound,0.73));
        series.getData().add(new XYChart.Data(frenc,1.00));
        series.getData().add(new XYChart.Data(A_Dollar,1.32));

        //Adding series to the barchart
        bar.getData().add(series);



    }

    @FXML
    void showstat(MouseEvent event) {
        Connection c;

        try {
            c = MyDB.getInstance().getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from MARCHANDISE";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                oblist.add(new Marchandise(rs.getInt("id_marchandise"), rs.getString("categorie_marchandise"), rs.getString("nom_marchandise"), rs.getString("date_arrive"), rs.getFloat("quantite"), rs.getFloat("prix_unitaire_marchandise"), rs.getFloat("prix_total_marchandise"), rs.getInt("Id_fournisseur")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

     }

        //******************PARTIE FOURNISSEUR******************
    @FXML
    void loadimage(ActionEvent event) throws MalformedURLException {

        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = chooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            text_path.setText(chooser.getSelectedFile().getAbsolutePath());
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            String localURL = file.toURI().toURL().toString();
            img_preview.setImage(new Image(localURL));

        }
    }

}
