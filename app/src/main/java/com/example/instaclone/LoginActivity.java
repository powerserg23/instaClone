package com.example.instaclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                login(username,password);
            }
        });
    };

    @Override
    protected void onResume() {
        super.onResume();
        ParseUser currentUser=ParseUser.getCurrentUser();
        if(currentUser!=null)
        {
            goMainActivity();
        }
        else{
            btnLogin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String username=etUsername.getText().toString();
                    String password=etPassword.getText().toString();
                    login(username,password);
                }
            });
        }
    }

    public void login(String username, String password)
    {
        //todo:: navtifate to new activity if the user has signed properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null)
                {
                    Log.e(TAG, "issue with login");
                    e.printStackTrace();
                    return;
                }
                goMainActivity();
            }


        });
    }
    private void goMainActivity() {
        Log.e(TAG, "Navigating to main activity");
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }
}
