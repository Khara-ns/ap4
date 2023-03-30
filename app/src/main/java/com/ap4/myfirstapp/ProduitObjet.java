package com.ap4.myfirstapp;

public class ProduitObjet {

    static String produitNom;
    static String produitPhoto;

    public ProduitObjet(String nomProduit, String photoProduit) {
        produitNom = nomProduit;
        produitPhoto = photoProduit;
    }

    public String getProduitNom() {
        return produitNom;
    }

    public String getProduitPhoto() {
        return "https://site.btsap3.tk/SiteFichier/ap3/imageproduit/"+produitPhoto;
    }


}
