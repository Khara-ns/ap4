package com.ap4.myfirstapp;

public class ProduitUniqueObjet {

    static String produitNom;
    static String produitRef;
    static int produitPrix;
    static String produitDesc;
    static String produitRayon;
    static String produitLieuStockage;

    public ProduitUniqueObjet(String nom , String ref , int prix , String description , String rayon , String lieuStockage) {
        System.out.println("Le :" + nom );
        produitNom = nom;
        produitRef = ref;
        produitPrix = prix;
        produitDesc = description;
        produitRayon = rayon;
        produitLieuStockage = lieuStockage;
    }
}
