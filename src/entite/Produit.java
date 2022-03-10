/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author ahmed amine
 */
public class Produit {
    private int id_produit;
    private String nom_produit;
    private String description_produit;
        private String image_produit;
    private String lien_produit;
    private float prix_produit;
    private String categorie_produit;

    public Produit() {
    }
    
    

    public Produit(int id_produit, String nom_produit, String description_produit, String image_produit, float prix_produit, String categorie_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
        this.prix_produit = prix_produit;
        this.categorie_produit = categorie_produit;
    }

    public Produit(int id_produit, String nom_produit, String description_produit, String image_produit, String lien_produit, float prix_produit, String categorie_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
        this.lien_produit = lien_produit;
        this.prix_produit = prix_produit;
        this.categorie_produit = categorie_produit;
    }

    public Produit(String nom_produit, String description_produit, String image_produit, String lien_produit, float prix_produit, String categorie_produit) {
        this.nom_produit = nom_produit;
        this.description_produit = description_produit;
        this.image_produit = image_produit;
        this.lien_produit = lien_produit;
        this.prix_produit = prix_produit;
        this.categorie_produit = categorie_produit;
    }

    
    public String toStringclient() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", description_produit=" + description_produit + ", image_produit=" + image_produit + ", lien_produit="  + prix_produit + ", categorie_produit=" + categorie_produit + '}';
    }
 public String toStringadmin() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", description_produit=" + description_produit + ", image_produit=" + image_produit + ", lien_produit=" + lien_produit + ", prix_produit=" + prix_produit + ", categorie_produit=" + categorie_produit + '}';
    }
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription_produit() {
        return description_produit;
    }

    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public String getLien_produit() {
        return lien_produit;
    }

    public void setLien_produit(String lien_produit) {
        this.lien_produit = lien_produit;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String getCategorie_produit() {
        return categorie_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }
    
    

}
