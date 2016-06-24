package com.example.yangzhe.data;

/**
 * Created by yangzhe on 16-6-24.
 */
public class InternetImageData {
    private int width;
    private int height;
    private String picUrl;               // the picture's address
    private String title;                // the picture's title
    private String description;          // the picture's description

    public InternetImageData(int width,int height,String picUrl){
        this.width = width;
        this.height = height;
        this.picUrl = picUrl;
    }

    public InternetImageData(String picUrl,String title){
        this.picUrl = picUrl;
        this.title = title;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getPicUrl(){
        return picUrl;
    }

    public String getTitle(){
        return title;
    }
}
