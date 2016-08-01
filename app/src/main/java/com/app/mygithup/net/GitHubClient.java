package com.app.mygithup.net;

import com.app.mygithup.login.model.User;
import com.app.mygithup.login.model.AccessTokenResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2016/7/28.
 */
public class GitHubClient implements GitHubApi {

    private GitHubApi gitHubApi;
    private static GitHubClient gitHubClient;

    public static GitHubClient getInstance() {
        if (gitHubClient == null) {
            gitHubClient = new GitHubClient();
        }
        return gitHubClient;
    }

    private GitHubClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(new TokenInterceptor())
        .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubApi = retrofit.create(GitHubApi.class);
    }


    @Override
    public Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client, @Field("client_secret") String clientSecret, @Field("code") String code) {
        return gitHubClient.getOAuthToken(client,clientSecret,code);
    }

    @Override
    public Call<User> getUserInfo() {
        return gitHubApi.getUserInfo();
    }
}
