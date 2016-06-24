package com.example.yangzhe.learnjson;

import com.example.yangzhe.data.InternetImageData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhe on 16-6-24.
 */
public class JsonParser {
    /**
     * Get the picture url from json
     * @param json the json from baidu meinv api server
     * @param numberOfPicture the number of picture which be requested
     * @return picUrlList   a list which store the picture's url
     * */
    public static ArrayList<String> getPicUrlFromJson(String json,int numberOfPicture){
        ArrayList<String> picUrlList = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("newslist");
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String picUrl = jsonObject1.getString("picUrl");
                picUrlList.add(picUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return picUrlList;
    }

    /**
     * Get the picture url from json
     * @param json the json from baidu meinv api server
     * @param numberOfPicture the number of picture which be requested
     * @return picInfoList   a list which store the picture's url
     * */
    public static ArrayList<InternetImageData> getInternetPicInfoFromJson(String json, int numberOfPicture){
        ArrayList<InternetImageData> picInfoList = new ArrayList<InternetImageData>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("newslist");
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String picUrl = jsonObject1.getString("picUrl");
                String title = jsonObject1.getString("title");
                InternetImageData internetImageData = new InternetImageData(picUrl,title);
                picInfoList.add(internetImageData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return picInfoList;
    }
}
