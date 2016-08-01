package com.app.mygithup.login;

import com.app.mygithup.login.model.User;

/**
 * 用来缓存当前用户信息
 */
public class UserRepo {
    private UserRepo(){}

    private static String accessToken;

    private static User user;

    // 判断是否有token
    public static boolean hasAccessToken(){
        return accessToken != null;
    }
    // 判断是否为空(还没有登陆)
    public static boolean isEmpty(){
        return accessToken == null || user == null;
    }
    // 清除token信息
    public static void clear(){
        accessToken = null;
        user = null;
    }


    public static void setAccessToken(String accessToken) {
        UserRepo.accessToken = accessToken;
    }

    public static void setUser(User user) {
        UserRepo.user = user;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static User getUser() {
        return user;
    }
}
