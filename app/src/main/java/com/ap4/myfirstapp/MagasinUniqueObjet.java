package com.ap4.myfirstapp;

public class MagasinUniqueObjet {

    static String produitNom;
    static String produitDesc;
    static String produitLieu;
    static String latitude;
    static String longitude;


    public MagasinUniqueObjet(String nom , String lieu , String description , String latitude , String longitude) {
        System.out.println("Le :" + nom );
        produitNom = nom;
        produitLieu = lieu;
        produitDesc = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
