package com.nikart.screens.auth.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nikart.interactor.Answer;
import com.nikart.interactor.loaders.AuthLoader;
import com.nikart.myshows.R;
import com.nikart.screens.main.MainActivity;
import com.nikart.util.PreferencesWorker;

import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signInButton;
    private EditText loginEditText;
    private EditText passwordEditText;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initActivity();
    }

    @Override
    public void onClick(View view) {
        LoaderManager.LoaderCallbacks loaderCallbacks = new LoaderManager.LoaderCallbacks<Answer>() {

            @Override
            public Loader<Answer> onCreateLoader(int i, Bundle bundle) {
                String[] params = bundle.getStringArray(AuthLoader.AUTH_ARGS);

                if (params != null) {
                    return new AuthLoader(LoginActivity.this, params[0], params[1]);
                }
                return new AuthLoader(LoginActivity.this, "0", "0");
            }

            @Override
            public void onLoadFinished(Loader<Answer> loader, Answer data) {
                Response response = data.getTypedAnswer();
                if (response != null && response.isSuccessful()) {
                    PreferencesWorker.getInstance().saveSignedIn(true);
                    Log.d("OkHTTP", "Successful");
                    MainActivity.start(LoginActivity.this);
                } else {
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.incorrect_data),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onLoaderReset(Loader<Answer> loader) {

            }
        };

        if (!loginEditText.getText().toString().equals("")
                && !passwordEditText.getText().toString().equals("")) {
            getSupportLoaderManager().restartLoader(0,
                    AuthLoader.args(loginEditText.getText().toString(),
                            passwordEditText.getText().toString()),
                    loaderCallbacks);
        } else {
            Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        }
    }

    private void initActivity() {
        signInButton = (Button) findViewById(R.id.sign_in_btn);
        loginEditText = (EditText) findViewById(R.id.login_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        signInButton.setOnClickListener(this);
    }
}
