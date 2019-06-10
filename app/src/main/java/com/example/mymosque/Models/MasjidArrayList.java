package com.example.mymosque.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MasjidArrayList
{
    @SerializedName("data")
    private ArrayList<MasjidModel> masjidModelArrayList;

    @SerializedName("links")
    private LinksModel linksModel;

    @SerializedName("meta")
    private MetaModel metaModel;
}
