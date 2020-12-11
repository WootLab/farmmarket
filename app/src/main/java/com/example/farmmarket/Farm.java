package com.example.farmmarket;

import java.io.Serializable;

public class Farm implements Serializable {

    private String location;
    private double lat;
    private double ltd;
    private String title;
    private String description;
    private String image;


    public Farm(){

    }
    public Farm(String location, String title, String description, String image, double lat, double ltd) {
        this.location = location;
        this.title = title;
        this.description = description;
        this.image = image;
        this.lat = lat ;
        this.ltd = ltd ;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Double getLtd() {
        return ltd;
    }

    public void setLtd(double ltd) {
        this.ltd = ltd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
