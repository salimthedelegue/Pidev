/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.services;

/**
 *
 * @author salim
 */
import java.util.List;

public interface SMservices<T> {
       public void ajouter(T m);
    public List<T> afficher();
    public void modifer(T m);
    public void supprimer(int id);
    
}
