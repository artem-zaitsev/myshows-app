package com.nikart.screens.auth.signin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nikart.myshows.R;
import com.nikart.presenter.DaggerPresenterComponent;
import com.nikart.presenter.login.LoginPresenter;
import com.nikart.screens.BaseActivity;
import com.nikart.screens.launch.LaunchActivity;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    public LoginPresenter presenter;
    private Button signInButton;
    private EditText loginEditText;
    private EditText passwordEditText;
    private ImageButton vkAuthButton;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void injectPresenter() {
        DaggerPresenterComponent.create().inject(this);
        presenter.onCreate(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setPresenter(presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_btn:
                getPresenter().loadData();
                break;
            case R.id.vk_auth_btn:
                VKSdk.login(this);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "screen_name"))
                        .executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        try {
                            JSONObject fromVk = response.json.getJSONArray("response").getJSONObject(0);
                            Log.d("VK", "screen_name: " + fromVk.get("screen_name") );
                            presenter.signInByVk(res, fromVk.getString("id"));
                            findViewById(R.id.activity_login_progress).setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Log.d("VK", e.toString());
                        }
                    }
                });
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void initActivity() {
        signInButton = (Button) findViewById(R.id.sign_in_btn);
        vkAuthButton = (ImageButton) findViewById(R.id.vk_auth_btn);
        loginEditText = (EditText) findViewById(R.id.login_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        signInButton.setOnClickListener(this);
        vkAuthButton.setOnClickListener(this);
    }

    public void startActivityIfSignedIn(Boolean isSuccessful) {
        PreferencesWorker.getInstance().saveSignedIn(isSuccessful);
        MainActivity.start(LoginActivity.this);
        finish();
    }

    public String getLogin() {
        return loginEditText.getText().toString();
    }

    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public <T> void showData(T data) {
        startActivityIfSignedIn((Boolean) data);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void onBackPressed() {
        LaunchActivity.start(this);
        finish();
    }
}
