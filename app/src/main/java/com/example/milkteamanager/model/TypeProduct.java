package com.example.milkteamanager.model;

public class TypeProduct {
    public int idtype;
    public String name;

    public  String image;

    public TypeProduct(int idtype, String name, String image) {
        this.idtype = idtype;
        this.name = name;
        this.image = image;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
