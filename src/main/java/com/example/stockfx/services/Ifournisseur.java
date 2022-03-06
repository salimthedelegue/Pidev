package com.example.stockfx.services;

import java.sql.SQLException;
import java.util.List;

public interface Ifournisseur <F>{
    public void ajouterFournisseur(F f) throws SQLException;
    //public void modifierFournisseur(F t);
  //  public void supprimerFournisseur(int id);
    //public List<F> afficherFournisseur() throws SQLException;

}
