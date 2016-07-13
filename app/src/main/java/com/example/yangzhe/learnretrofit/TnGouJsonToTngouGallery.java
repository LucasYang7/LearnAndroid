package com.example.yangzhe.learnretrofit;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;
import rx.functions.Functions;

/**
 * Created by yangzhe on 16-7-13.
 */
public class TnGouJsonToTngouGallery implements Func1<TngouJson,List<TaingouGallery>>{

    @Override
    public List<TaingouGallery> call(TngouJson tngouJson) {
        /*
        List<TaingouGallery> taingouGalleries = tngouJson.taingouGalleryList;
        List<TaingouGallery> taingouGalleryList = new ArrayList<TaingouGallery>(taingouGalleries.size());
        return taingouGalleries;*/
        return tngouJson.taingouGalleryList;
    }
}
