package com.example.stockfx;
import com.example.stockfx.entities.Fournisseur;
import com.example.stockfx.services.ServiceFournisseur;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
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
import java.awt.Desktop;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.example.stockfx.entities.Marchandise;
import com.example.stockfx.services.ServiceStock;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

import javafx.util.Duration;
import utils.MyDB;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Tab;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

//part fournisseur
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.management.Notification;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.mail.Message.RecipientType;
import org.controlsfx.control.Notifications;
import com.twilio.Twilio;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
public class HelloController implements Initializable {
    private String path;
    File selectedFile;
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
    //***********Partie Fournisseur**********
    Fournisseur fournisseur = null;
    @FXML
    private TableView<Fournisseur> table_fournisseur;
    @FXML
    private JFXTextArea in_email;

    @FXML
    private JFXTextArea in_matricule;

    @FXML
    private JFXTextArea in_nom_fournisseur;

    @FXML
    private JFXTextArea in_num_fix;

    @FXML
    private JFXTextArea in_num_tel;
    @FXML
    private ComboBox<String> combo_typeveh;
    @FXML
    private TableColumn<Fournisseur, String> edit_four_col;

    @FXML
    private TableColumn<?, ?> email_four_col;
    @FXML
    private TableColumn<?, ?> id_fourn_col;
    @FXML
    private TableColumn<?, ?> matricule_four_col;

    @FXML
    private TableColumn<?, ?> num_fixfour_col;

    @FXML
    private TableColumn<?, ?> num_telfour_col;

    @FXML
    private TableColumn<Fournisseur, String> photo_four_col;
    @FXML
    private TableColumn<?, ?> nom_fournisseur_col;




    @FXML
    private Tab tab1;
    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("pick one", "PS DVD", "Hardware components", "Gears", "Streaming related");
    private ObservableList<ObservableList> data;
    private ObservableList<Marchandise> oblist = FXCollections.observableArrayList();
    private ObservableList<Marchandise> liststat = FXCollections.observableArrayList();
    private ObservableList<String> marchNames = FXCollections.observableArrayList();
    //***********Partie Fournisseur**********
    private ObservableList<String> dbTypeVehicule = FXCollections.observableArrayList("Camion", "Voiture", "Moto");
    private ObservableList<Fournisseur> oblistFourn = FXCollections.observableArrayList();
    public static final String ACCOUNT_SID = "AC6d45e72872be4dc89b31f64632235722";
    public static final String AUTH_TOKEN = "7829dcb9e8a557f28e8638e345d254c1";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        incategorie.setItems(dbTypeList);
        combo_typeveh.setItems(dbTypeVehicule);
        loadDate();
        loadDataFournisseur();
        Connection c;
        //************Statistiques Marchanidses**************

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
        boolean isMyQuantityValide = isreal(inqunatite.getText());
        boolean isMyPriceUValide = isreal(inprixunitaire.getText());
        boolean isMyPriceTValide = isreal(Inprixtotal.getText());
        boolean isMyFournisseurValide = isinte(inidfournisseur.getText());
        if (isMyNameValide==true && isMyQuantityValide==true && isMyPriceUValide==true && isMyPriceTValide==true && indate.getValue()!=null && incategorie.getValue()!=null && Innom.getText()!=null && inqunatite.getText()!=null && inprixunitaire.getText()!=null && Inprixtotal.getText()!=null && inidfournisseur.getText()!=null && isMyFournisseurValide==true) {
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

                //************NOTIFICATION**************

//                Image imgn =new Image("C:\\Users\\Sana\\Downloads\\v2withtableview\\Stockfx\\src\\main\\resources\\com\\example\\stockfx\\checkMark.png");
//                Notifications notificationBuilder= Notifications.create()
//                        .title("taskcomplete")
//                        .text("Added Sucess")
//                        .graphic(new ImageView(imgn))
//                        .hideAfter(Duration.seconds(5))
//                        .position(Pos.CENTER);
//                notificationBuilder.darkStyle();
//                notificationBuilder.show();


//************NOTIFICATION**************

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
            sendAttachmentEmail();
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
      sendAttachmentEmail();
        try {
            c = MyDB.getInstance().getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from MARCHANDISE";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            oblist.clear();
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
/*
    @FXML
    void showstat(MouseEvent event) {
        WritableImage image = barChart.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        File file = new File("chart.png");

        try {

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }

     }
*/
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
    void AddFour(ActionEvent event) {
            //boolean isMyDateValide=isdate(String.valueOf(indate.getValue().format(DateTimeFormatter.ofPattern("yy-MMM-dd"))));
            boolean isMyNameFourValide = isName(in_nom_fournisseur.getText());
            boolean isMYNUmTelValide=isphonenumber(in_num_tel.getText());
            boolean isMYNumFixValide=isphonenumber(in_num_fix.getText());
            boolean isMyMatriculeValide=ismatricule(in_matricule.getText());
            boolean isMyEmailValide=isemail(in_email.getText());
            String fromFile = text_path.getText();
            File file = new File(text_path.getText());
            if (in_nom_fournisseur.getText()!=null && in_num_tel.getText()!=null && in_num_fix.getText()!=null && in_matricule.getText()!=null  && isMyNameFourValide==true && isMYNUmTelValide==true && isMYNumFixValide==true && isMyMatriculeValide==true&& isMyEmailValide==true && text_path.getText()!=null) {
                ServiceFournisseur sf = new ServiceFournisseur();
                Fournisseur f = new Fournisseur(in_nom_fournisseur.getText(),in_num_tel.getText(), in_num_fix.getText(), in_email.getText(), in_matricule.getText(),file.getName());
                System.out.println(text_path.getText());
                try {

                    String toFile = "C:\\xampp\\htdocs\\stockfxphotofournisseur\\"+file.getName();
                    Path source = Paths.get(fromFile);
                    Path target = Paths.get(toFile);
                    try {
                        Files.move(source, target);
                         // if target exists, replace it.
                        // Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }


                    sf.ajouterFournisseur(f);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("added successfully!");
                    alert.show();
                    //incategorie.set(0);
                    in_nom_fournisseur.setText("");
                    in_num_tel.setText("");
                    in_num_fix.setText("");
                    in_email.setText("");
                    in_matricule.setText("");
                    text_path.setText("");
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
    private void loadDataFournisseur() {
        Connection c;
        c = MyDB.getInstance().getConnection();
        Refrechtablefournisseur();
        id_fourn_col.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));
        nom_fournisseur_col.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        num_telfour_col.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        num_fixfour_col.setCellValueFactory(new PropertyValueFactory<>("num_fixe"));
        email_four_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        matricule_four_col.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        photo_four_col.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("photo"));

        //cell of button imaga

        //add cell of button edit
              
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFoctory = (TableColumn<Fournisseur, String> param) -> {
            // make cell containing buttons
            final TableCell<Fournisseur, String> cell = new TableCell<Fournisseur, String>() {
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
                                fournisseur = table_fournisseur.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `Fournisseur` WHERE id_fournisseur  =" + fournisseur.getId_fournisseur();
                                PreparedStatement ps = cnx.prepareStatement(query);
                                ps.execute();
                                Refrechtablefournisseur();

                            } catch (SQLException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            fournisseur = table_fournisseur.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Editfournisseur.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            EditfournisseurController editControllerfourniss = loader.getController();
                            editControllerfourniss.setUpdate(true);
                            editControllerfourniss.setTextField(fournisseur.getId_fournisseur(), fournisseur.getNom_fournisseur(), fournisseur.getNum_telephone(),
                                    fournisseur.getNum_fixe(), fournisseur.getEmail(), fournisseur.getMatricule(),fournisseur.getPhoto());
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
        edit_four_col.setCellFactory(cellFoctory);
        table_fournisseur.setItems(oblistFourn);

        //photo_four_col.setCellFactory(cellFactoryImage);
        Callback<TableColumn<Fournisseur, String>, TableCell<Fournisseur, String>> cellFactoryImage = (TableColumn<Fournisseur, String> param) -> {
            // make cell containing buttons
            final TableCell<Fournisseur, String> cellImg = new TableCell<Fournisseur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    Connection c;
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else  {

                            //System.out.println("img "+getTableRow().getItem().getPhoto());
                            String pathImg = "C:\\xampp\\htdocs\\stockfxphotofournisseur\\" + item;
                            ImageView imagev = new ImageView(new Image(pathImg));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);



                    }
                }


            };

            return cellImg;
        };
        photo_four_col.setCellFactory(cellFactoryImage);

    }
    @FXML
    public void Refrechtablefournisseur() {

        //notifier("Good!", "It's working now!");
        Connection c;

        try {
            c = MyDB.getInstance().getConnection();
            String SQL = "SELECT * from FOURNISSEUR";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            oblistFourn.clear();
            while (rs.next()){
                oblistFourn.add(new Fournisseur(rs.getInt("id_fournisseur"),rs.getString("nom_fournisseur"),rs.getString("numtel_fournisseur"),rs.getString("numFixe_fournisseur"),rs.getString("email"),rs.getString("matricule_fiscale"),rs.getString("photo")));
            }


        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void notifier(String pTitle, String pMessage) {
        Platform.runLater(() -> {
                    Stage owner = new Stage(StageStyle.TRANSPARENT);
                    StackPane root = new StackPane();
                    root.setStyle("-fx-background-color: TRANSPARENT");
                    Scene scene = new Scene(root, 1, 1);
                    scene.setFill(Color.TRANSPARENT);
                    owner.setScene(scene);
                    owner.setWidth(1);
                    owner.setHeight(1);
                    owner.toBack();
                    owner.show();
                    Notifications.create().title(pTitle).text(pMessage).showInformation();
                }
        );
    }
    /*
    private static void sendHTMLEmail(){
            // Recipient's email ID needs to be mentioned.
            String to = "azouzim80@gmail";

            // Sender's email ID needs to be mentioned
            String from = "mohamedali.azouzi@esprit.tn";

            // Assuming you are sending email from localhost
            String host = "localhost";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host);

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Send the actual HTML message, as big as you like
                message.setContent("<h1>This is actual message</h1>", "text/html");

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }

     */
    /*
    public void sendAttachmentEmail(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@stock.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("mohamedali.azaouzi@esprit.tn", false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(body);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "file:///C:/Users/Sana/Desktop/marchandisestest.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with attachment!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

     */
    public void sendAttachmentEmail() {
        final String userID = "sdali5082@gmail.com";
        final String userPass = "Dali 1200";
        final String emailTo = "azouzim80@gmail.com";
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userID, userPass);
                    }
                });
        Multipart multipart;
        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Hello, this is a test mail..");

            multipart = new MimeMultipart();
            String fileName = "marchandisestest.pdf";

            addAttachment(multipart, fileName);

            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("No need to reply.");
            multipart.addBodyPart(messageBodyPart1);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email successfully sent to: " + emailTo);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

        private static void addAttachment(Multipart multipart, String fileName){
            DataSource source = null;
            File f = new File("C:/Users/Sana/Desktop" + "/" + fileName);
            if (f.exists() && !f.isDirectory()) {
                source = new FileDataSource("C:/Users/Sana/Desktop" + "/" + fileName);
                BodyPart messageBodyPart = new MimeBodyPart();
                try {
                    messageBodyPart.setHeader("Content-Type", "text/html");
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(fileName);
                    multipart.addBodyPart(messageBodyPart);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

}
