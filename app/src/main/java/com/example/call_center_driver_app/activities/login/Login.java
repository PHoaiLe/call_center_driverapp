package com.example.call_center_driver_app.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.call_center_driver_app.R;
import com.example.call_center_driver_app.my_interfaces.ChainHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class Login extends AppCompatActivity {

    private TextInputEditText account, password;
    private MaterialButton loginButton;
    private MaterialTextView linkToSignUp;
    private ChainHandler commandHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account = findViewById(R.id.login_account_input);
        password = findViewById(R.id.login_password_input);
        loginButton = findViewById(R.id.login_login_button);
        linkToSignUp = findViewById(R.id.login_goto_signup_link);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

}