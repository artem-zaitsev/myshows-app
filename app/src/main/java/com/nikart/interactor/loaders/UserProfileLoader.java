package com.nikart.interactor.loaders;

import android.content.Context;
import android.util.Log;

import com.nikart.app.App;
import com.nikart.base.BaseLoader;
import com.nikart.data.dto.UserProfile;
import com.nikart.interactor.Answer;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Artem on 14.03.2017.
 */

public class UserProfileLoader extends BaseLoader<Answer> {

    public UserProfileLoader(Context context) {
        super(context);
    }

    @Override
    public Answer loadInBackground() {

        data = new Answer();
        try {
            Response<UserProfile> response = App.getInstance().getApi().getUserProfile().execute();
            UserProfile userProfile = response.body();
            Log.d("LOADERS", "UserResponse: " + String.valueOf(response.body()) + " " + response.message());
            data.setAnswer(userProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
