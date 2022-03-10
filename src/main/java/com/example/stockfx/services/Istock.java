
package com.example.stockfx.services;

import com.example.stockfx.entities.Marchandise;
import com.example.stockfx.entities.Fournisseur;

import java.sql.SQLException;
import java.util.List;


public interface Istock<T> {
    
    public void ajouter(T t) throws SQLException;

    public void modifier(T t);
    public void supprimer(int id);
    public List<T> afficher() throws SQLException;
    Marchandise selectById(int id);
    public void insertgeneratepdf(T t);
}
