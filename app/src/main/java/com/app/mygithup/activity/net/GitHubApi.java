package com.app.mygithup.activity.net;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface GitHubApi {
    //通过注解来描述
    @GET("repos/square/retrofit/contributors")
    Call<ResponseBody> getRetrofitContributors();
}
