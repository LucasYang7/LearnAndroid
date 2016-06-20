package com.example.yangzhe.data;

/**
 * Created by yangzhe on 16-6-20.
 */
public class AlbumImageData {
    private int width;
    private int height;
    private String path;

    public AlbumImageData(int width,int height,String path){
        this.width = width;
        this.height = height;
        this.path  = path;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getPath(){
        return path;
    }
}
