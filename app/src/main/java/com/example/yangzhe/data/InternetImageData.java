package com.example.yangzhe.data;

/**
 * Created by yangzhe on 16-6-24.
 */
public class InternetImageData {
    private int width;
    private int height;
    private String url;               // the picture's address

    public InternetImageData(int width,int height,String url){
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public InternetImageData(String url){
        this.url = url;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getUrl(){
        return url;
    }
}
