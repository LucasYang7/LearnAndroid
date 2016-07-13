package com.example.yangzhe.learnretrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yangzhe on 16-7-13.
 */
public class TngouJson {
    public boolean status;
    public @SerializedName("tngou") List<TaingouGallery> taingouGalleryList;
}
