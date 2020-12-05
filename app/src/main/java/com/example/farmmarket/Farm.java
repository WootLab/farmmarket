package com.example.farmmarket;

public class Farm {

    private String location;
    private String title;
    private String description;
    private String image;


    public Farm(){

    }
    public Farm(String location, String title, String description, String image) {
        this.location = location;
        this.title = title;
        this.description = description;
        this.image = image;
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
