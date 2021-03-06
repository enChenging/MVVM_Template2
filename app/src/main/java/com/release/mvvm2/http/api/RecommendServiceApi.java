package com.release.mvvm2.http.api;


import com.release.mvvm2.bean.RecommendPageBean;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Mr.release
 * @create 2019/3/29
 * @Describe
 */
public interface RecommendServiceApi {

    @POST("it")
    Flowable<RecommendPageBean> getRecommendData(@Query("key") String key, @Query("num") int num);
}
