package com.nikart.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.interactor.interceptors.AddCookiesInterceptor;
import com.nikart.interactor.interceptors.ReceivedCookieInterceptor;
import com.nikart.util.Md5Converter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 11.03.2017.
 * Loader для авторизации. Пока забиты только тестовые данные.
 */

public class AuthLoader extends BaseLoader<Answer> {

    private String login;
    private String password;
    private OkHttpClient client;

    public static Bundle args(String login, String password) {
        Bundle args = new Bundle();
        String[] array = {login, password};
        args.putStringArray("ARGS", array);
        return args;
    }

    /*Правильно ли передавать логин и пароль в конструктор лоадеру?
    * Есть ли какой-нибудь другой способ?*/
    public AuthLoader(Context context, String login, String password) {
        super(context);
        this.login = login;
        this.password = password;
    }

    @Override
    public Answer loadInBackground() {
        Answer answer = new Answer();
        client = App.getInstance().getClient();

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
