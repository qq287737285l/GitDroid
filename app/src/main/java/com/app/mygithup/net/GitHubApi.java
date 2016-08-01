package com.app.mygithup.net;

import com.app.mygithup.login.model.User;
import com.app.mygithup.login.model.AccessTokenResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface GitHubApi {
    String CALL_BACK = "http://www.18234132955@163.com";
    String CLIENT_ID = "6e3e52112ebddb0e55cf";
    String CLIENT_SECRET = "91533a6e06014cbcc7190ab33caa07223eb6a4c9";
    String AUTH_SCOPE = "user,public_repo,repo";
    //授权登录页面
    String AUTH_RUL = "https://github.com/login/oauth/authorize?client_id="
            + CLIENT_ID + "&scope=" + AUTH_SCOPE;

    //通过注解来描述
    //获取token api
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client,
                                          @Field("client_secret") String clientSecret,
                                          @Field("code") String code);

    @GET("user")
    Call<User> getUserInfo();
}
