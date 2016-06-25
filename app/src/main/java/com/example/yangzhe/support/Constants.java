package com.example.yangzhe.support;

/**
 * Created by yangzhe on 16-6-24.
 */
public interface Constants {
    /**
     * the constants of baidu Meinv Api
     * */
    interface ApiBaiduMeinvHttpConstants{
        String httpUrl = "http://apis.baidu.com/txapi/mvtp/meinv";
        String httpArg = "num=";
        int numberOfPicture = 20;

        String failToGetJson = "Fail to get json object,it seems you can not connect to the server.";
    }
}
