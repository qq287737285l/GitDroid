package com.app.mygithup.login;


import com.app.mygithup.login.model.User;
import com.app.mygithup.login.model.AccessTokenResult;
import com.app.mygithup.net.GitHubApi;
import com.app.mygithup.net.GitHubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/7/29.
 */
public class LoginPresenter {
    private Call<AccessTokenResult> accessTokenResultCall;
    private Call<User> userCall;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    //登陆 用返回码换授权 授权换用户信息
    public void login(String code) {
        loginView.showProgress();
        if (accessTokenResultCall != null)
            accessTokenResultCall.cancel();
        //获取授权
        accessTokenResultCall = GitHubClient.getInstance().getOAuthToken(GitHubApi.CLIENT_ID, GitHubApi.CLIENT_SECRET, code);
        accessTokenResultCall.enqueue(tokenCallBack);
    }

    private Callback<AccessTokenResult> tokenCallBack = new Callback<AccessTokenResult>() {
        @Override
        public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            AccessTokenResult result = response.body();
            String token = result.getAccessToken();
            UserRepo.setAccessToken(token);

            if (userCall != null)
                userCall.cancel();
            userCall = GitHubClient.getInstance().getUserInfo();
            userCall.enqueue(userCallBack);
        }

        @Override
        public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
        }
    };
    private Callback<User> userCallBack = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            User user = response.body();
            UserRepo.setUser(user);
            loginView.showMessage("Login succeed!");
            loginView.navigateToMain();
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
        }
    };
}
