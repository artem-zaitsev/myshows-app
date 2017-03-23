package com.nikart.interactor.loaders;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.interactor.Answer;
import com.nikart.util.Md5Converter;
import com.nikart.util.PreferencesWorker;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Artem on 11.03.2017.
 * Loader для авторизации. Пока забиты только тестовые данные.
 */

public class AuthLoader extends BaseLoader<Answer> {

    public static final String AUTH_ARGS = "AUTH_ARGS";
    private String login;
    private String password;
    private OkHttpClient client;

    /*Правильно ли передавать логин и пароль в конструктор лоадеру?
    * Есть ли какой-нибудь другой способ?*/
    public AuthLoader(Context context, String login, String password) {
        super(context);
        this.login = login;
        this.password = password;
    }

    public static Bundle args(String login, String password) {
        Bundle args = new Bundle();
        String[] array = {login, password};
        args.putStringArray(AUTH_ARGS, array);
        return args;
    }

    @Override
    public Answer loadInBackground() {
        Answer answer = new Answer();
        client = App.getInstance().getClient();

        /*Подставляем логин и пароль из EditText'ов*/
        Request request = new Request.Builder()
                .url("https://api.myshows.me/profile/login?login=" + login + "&password=" +
                        Md5Converter.Md5Hash(password))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Log.d("OkHTTP", response.toString());
            Log.d("HTTP_RESPONSE", response.headers().get("Set-Cookie"));
            if (response.isSuccessful()){
                PreferencesWorker.getInstance().saveLogin(login);
                PreferencesWorker.getInstance().savePassword(password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer.setAnswer(response);
        return answer;
    }
}
