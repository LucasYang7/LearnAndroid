package com.example.yangzhe.learnretrofit;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangzhe on 16-7-12.
 * Retrofit 将Http API转换为一个Java interface
 */
public interface TngouAPI {
    @GET("tnfs/api/classify")                  // Zhuangbi　Api的URL所对应的子路径.  http://zhuangbi.info/search
    Observable<TngouJson> search();  // @Query对应着HTTP GET请求操作的参数.http://zhuangbi.info/search?q=query
}
