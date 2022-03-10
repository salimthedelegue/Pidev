
package com.example.stockfx.services;


import com.example.stockfx.entities.Marchandise;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

import java.io.FileOutputStream;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServiceStock implements Istock<Marchandise> {

    Connection cnx;

    public ServiceStock() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Marchandise t) throws SQLException{
        try {
            String req = "insert into marchandise (nom_marchandise,categorie_marchandise,date_arrive,quantite,prix_unitaire_marchandise,prix_total_marchandise,id_fournisseur) values"
                    + " ( '" + t.getNom_marchandise() + "','" + t.getCategorie_marchandise() + "', '" + t.getDate_arrive() + "','" + t.getQuantite() + "', '" + t.getPrix_total_marchandise() + "', '" + t.getPrix_total_marchandise() + "','" + t.getId_fournisseur() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public void modifier(Marchandise t) {
        try {
            String req = "update marchandise set categorie_marchandise = ? , nom_marchandise = ? , quantite = ?  where id_marchandise = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getCategorie_marchandise());
            ps.setString(2, t.getNom_marchandise());
            ps.setDouble(3, t.getQuantite());
            ps.setInt(4, t.getId_marchandise());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id_marchandise) {
        try {
            String req = "delete from marchandise where id_marchandise = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_marchandise);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Marchandise selectById(int id) {
        Marchandise march = new Marchandise();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = cnx.prepareStatement("SELECT * FROM marchandise WHERE id_marchandise = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                march.setId_marchandise(resultSet.getInt("id_marchandise"));
                march.setNom_marchandise(resultSet.getString("nom_marchandise"));
                march.setDate_arrive(resultSet.getString("Date_arrive"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return march;
    }


    @Override
    public void insertgeneratepdf(Marchandise t){
        List<Marchandise> list = new ArrayList<>();
        //created PDF document instance
        Document doc = new Document();
        doc.addAuthor("Med Ali");
        doc.addCreationDate();
        doc.addCreator("azouzi");
        doc.addTitle("Marchandises");
        doc.addSubject("Suivi Marchandises");
        try {
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
            //generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Sana/Downloads/marchandises1.pdf"));
            System.out.println("PDF created.");
            //opens the PDF
            doc.open();
            // Creating a path choosing file from local
            // directory by creating an object of Path class
            Path fileName = Path.of("C:/Users/Sana/Downloads/stylehtml.html");

            // Now calling Files.readString() method to
            // read the file
            String HTML = Files.readString(fileName);
            //adds paragraph to the PDF file

            String result = list.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining("-", "{", "}"));
            HTMLWorker hw = new HTMLWorker(doc);
            hw.parse(new StringReader(HTML));
            //close the PDF file
            doc.close();
            //closes the writer
            writer.close();
        }
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

    @Override
    public List<Marchandise> afficher() throws SQLException {
        List<Marchandise> list = new ArrayList<>();
        try {
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
        return list;
    }


}
