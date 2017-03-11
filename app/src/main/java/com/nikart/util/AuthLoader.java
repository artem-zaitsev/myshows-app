package com.nikart.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.nikart.base.BaseAnswer;
import com.nikart.base.BaseLoader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 11.03.2017.
 * Loader для авторизации. Пока забиты только тестовые данные.
 */

public class AuthLoader extends BaseLoader<BaseAnswer> {

    private SharedPreferences cookiesPrefs;
    private String login;
    private String password;

    /*Правильно ли передавать логин и пароль в конструктор лоадеру?
    * Есть ли какой-нибудь другой способ?*/
    public AuthLoader(Context context, SharedPreferences prefs,
                      String login, String password) {
        super(context);
        cookiesPrefs = prefs;
        this.login = login;
        this.password = password;
    }

    @Override
    public BaseAnswer loadInBackground() {
        BaseAnswer answer = new BaseAnswer();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookieInterceptor())
                .build();


        /*Подставляем логин и пароль из EditText'ов*/
        Request request = new Request.Builder()
                .url("https://api.myshows.me/profile/login?login=" + login + "&password=" +
                        Md5Converter.MD5_Hash(password))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Log.d("OkHTTP", response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer.setAnswer(response);
        return answer;
    }
}
