package com.example.yangzhe.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yangzhe on 16-6-24.
 */
public class HttpOperation {
    /**
     * request the picture's url, and get the json from picture url
     * @param httpUrl   the url of picture
     * @param httpArg   the http request arguments
     * @param numberOfPicture   the number of picture which requested
     * @return result   the json result
     * */
    public static String request(String httpUrl,String httpArg,int numberOfPicture){
        BufferedReader bufferedReader = null;
        String result = null;
        StringBuffer stringBuffer = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg + numberOfPicture;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");         // set request method be GET
            httpURLConnection.setRequestProperty("apikey","deb67b8b22af44389cd951bf46655f99");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String strLine = null;
            while((strLine = bufferedReader.readLine())!=null){
                stringBuffer.append(strLine);
                stringBuffer.append("\r\n");
            }
            bufferedReader.close();
            result = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
