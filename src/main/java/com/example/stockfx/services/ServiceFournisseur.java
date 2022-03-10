package com.example.stockfx.services;
import com.example.stockfx.entities.Fournisseur;
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

public class ServiceFournisseur implements Ifournisseur<Fournisseur> {
    Connection cnx;

    public ServiceFournisseur() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterFournisseur(Fournisseur f) throws SQLException{
        try {

            String req = "insert into fournisseur (nom_fournisseur,numtel_fournisseur,numFixe_fournisseur,email,matricule_fiscale,photo) values"
                    + " ( '" + f.getNom_fournisseur() + "','" + f.getNum_telephone() + "', '" + f.getNum_fixe() + "','" + f.getEmail() + "', '" + f.getMatricule() + "', '" + f.getPhoto() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
