package com.example.mymosque.Models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class PrimraryMosqueData
{
    @SerializedName("ID")
    private int ID;
    @SerializedName("name")
    @NotNull
    private String name;
    @SerializedName("longtitude")
    @NotNull
    private String longtitude;
    @SerializedName("latitude")
    @NotNull
    private String latitude;
    @SerializedName("imageurl")
    @NotNull
    private String imageurl;
    @SerializedName("address")
    @NotNull
    private String address;
    @SerializedName("farvoriate")
    private int farvoriate;
    @SerializedName("farvoriate_id")
    private int farvoriate_id;
    @SerializedName("topics_name")
    @NotNull
    private String topics_name;

    public PrimraryMosqueData(int ID, @NotNull String name, @NotNull String longtitude, @NotNull String latitude, @NotNull String imageurl, @NotNull String address, int farvoriate, int farvoriate_id, @NotNull String topics_name) {
        this.ID = ID;
        this.name = name;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.imageurl = imageurl;
        this.address = address;
        this.farvoriate = farvoriate;
        this.farvoriate_id = farvoriate_id;
        this.topics_name = topics_name;
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

    public int getFarvoriate_id() {
        return farvoriate_id;
    }

    public void setFarvoriate_id(int farvoriate_id) {
        this.farvoriate_id = farvoriate_id;
    }

    public String getTopics_name() {
        return topics_name;
    }

    public void setTopics_name(String topics_name) {
        this.topics_name = topics_name;
    }
}
