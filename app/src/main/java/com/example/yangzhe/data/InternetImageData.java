package com.example.yangzhe.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangzhe on 16-6-24.
 */
public class InternetImageData implements Parcelable {
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

    protected InternetImageData(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        picUrl = in.readString();
        title = in.readString();
        description = in.readString();
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

    public static final Creator<InternetImageData> CREATOR = new Creator<InternetImageData>() {
        @Override
        public InternetImageData createFromParcel(Parcel in) {
            return new InternetImageData(in);
        }

        @Override
        public InternetImageData[] newArray(int size) {
            return new InternetImageData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(picUrl);
        dest.writeString(title);
        dest.writeString(description);
    }
}
