package com.example.volleyimage;

public class Produit {

    String ref, des;
    int prix;

    public Produit() {
    }

    public Produit(String ref, String des, int prix) {
        this.ref = ref;
        this.des = des;
        this.prix = prix;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
