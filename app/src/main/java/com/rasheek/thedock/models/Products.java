package com.rasheek.thedock.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Products implements Serializable {
    private  String name;
    private  String desc;
    private  String coverImage;
    private String stock;
    private  String price;

    private ArrayList<String> colors;



    private ArrayList<String> images;

    public Products() {
    }

    public Products(String name, String desc, String coverImage,  String stock, String price, ArrayList<String> colors, ArrayList<String> images) {
        this.name = name;
        this.desc = desc;
        this.coverImage = coverImage;
        this.stock = stock;
        this.price = price;
        this.colors = colors;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }


    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }



    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
