package com.example.instagramclone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword, edtUserNameLogin, edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.signup_login_activity);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            Toast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully", Toast.LENGTH_LONG).show();
                        }else
                        {

                            Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            Toast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in successfully", Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}
