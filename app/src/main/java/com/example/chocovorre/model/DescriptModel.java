package com.example.chocovorre.model;

public class DescriptModel {
    private String title;
    private String origin;
    private String composition;
    private String image_url;
    private int percentage;
    private int price;


    public DescriptModel(){
    }

    public DescriptModel(String title, String origin, String composition, String image_url, int percentage, int price) {

        this.title = title;
        this.origin = origin;
        this.composition = composition;
        this.image_url = image_url;
        this.percentage = percentage;
        this.price = price;
    }


    public String getTitle () {
        return title;
    }

    public String getOrigin () {
        return origin;
    }

    public String getComposition () {
        return composition;
    }

    public String getImage_url () {
        return image_url;
    }

    public int getPercentage () {
        return percentage;
    }

    public int getPrice () {
        return price;
    }




    public void setTitle (String name){
        this.title = name;
    }

    public void setOrigin (String origin){
        this.origin = origin;
    }


    public void setComposition (String composition){
        this.composition = composition;
    }


    public void setImage_url (String image_url){
        this.image_url = image_url;
    }

    public void setPercentage (int percentage){
        this.percentage = percentage;
    }

    public void setPrice (int price){
        this.price = price;
    }

}

