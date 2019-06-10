package com.example.mymosque.Models;

public class MasjidModel
{
    private int ID;
    private String name;
    private String longtitude;
    private String latitude;
    private String imageurl;
    private String address;
    private int farvoriate;

    public MasjidModel(int ID, String name, String longtitude, String latitude, String imageurl, String address, int farvoriate) {
        this.ID = ID;
        this.name = name;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.imageurl = imageurl;
        this.address = address;
        this.farvoriate = farvoriate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFarvoriate() {
        return farvoriate;
    }

    public void setFarvoriate(int farvoriate) {
        this.farvoriate = farvoriate;
    }
}
