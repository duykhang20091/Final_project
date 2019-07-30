package com.example.milkteamanager.model;

public class product {
    public int id;
    public String name;
    public int price;
    public String imageproduct;
    public String describeproduct;
    public int idtype;

    public product(int id, String name, int price, String imageproduct, String describeproduct, int idtype) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageproduct = imageproduct;
        this.describeproduct = describeproduct;
        this.idtype = idtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageproduct() {
        return imageproduct;
    }

    public void setImageproduct(String imageproduct) {
        this.imageproduct = imageproduct;
    }

    public String getDescribeproduct() {
        return describeproduct;
    }

    public void setDescribeproduct(String describeproduct) {
        this.describeproduct = describeproduct;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }
}
