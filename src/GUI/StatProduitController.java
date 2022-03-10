/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author ahmed amine
 */
public class StatProduitController implements Initializable {

    @FXML
    private PieChart piechart;
    private Connection conn;
    private Statement st;
        private ResultSet rs;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData2();
    }    
    private void loadData2(){
     ObservableList<PieChart.Data> pieChartData2=FXCollections.observableArrayList();
ProduitService ps=new ProduitService();     
   HashMap<String,Double> map=new HashMap<String,Double>();
map=ps.getcount();
     for(Map.Entry m : map.entrySet()){    
         
    //System.out.println(m.getKey()+" "+m.getValue());    
    pieChartData2.add(new PieChart.Data(String.valueOf(m.getKey()), (double) m.getValue()));

     }  
     
     piechart.setData(pieChartData2);
      piechart.setStartAngle(1500);
      for(final PieChart.Data data : piechart.getData()) {
                data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue());
       
}
}

    @FXML
    private void retour(ActionEvent event) {
        try{
         Parent root=FXMLLoader.load(getClass().getResource("/GUI/Produit.fxml"));
                        Scene scene = new Scene(root);
                        // aandi interface 9dima w bech n7el interface jdida w nsaker l 9dima donc nestaamel node
                        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Forgot Password!!");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListeproduitpanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
}
