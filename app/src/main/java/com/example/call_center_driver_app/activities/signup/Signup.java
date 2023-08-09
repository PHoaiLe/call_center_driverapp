package com.example.call_center_driver_app.activities.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.call_center_driver_app.R;
import com.example.call_center_driver_app.activities.signup.commands.SignupButtonCommand;
import com.example.call_center_driver_app.activities.signup.commands.SignupLinkToLogin;
import com.example.call_center_driver_app.activities.signup.constants.SignupConstants;
import com.example.call_center_driver_app.constants.GlobalCommandID;
import com.example.call_center_driver_app.my_interfaces.Command;
import com.example.call_center_driver_app.other_components.CommandHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    private TextInputEditText email, password, confirmPassword, phone, name;
    private MaterialButton signupButton, linkToLogin;

    private CommandHandler commandHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.signup_email_input);
        password = findViewById(R.id.signup_password_input);
        confirmPassword = findViewById(R.id.signup_confirm_password_input);
        phone = findViewById(R.id.signup_phone_input);
        linkToLogin = findViewById(R.id.signup_goto_login_link);
        signupButton = findViewById(R.id.signup_signup_button);
        name = findViewById(R.id.signup_name);

        initCommandHandler();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commandHandler.handle(GlobalCommandID.SIGNUP_BUTTON_COMMAND);
            }
        });

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commandHandler.handle(GlobalCommandID.LINK_TO_LOGIN);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    private void initCommandHandler()
    {
        CommandHandler linkToLoginHandler = new CommandHandler(GlobalCommandID.LINK_TO_LOGIN);
        Command linkToLoginCommand = new SignupLinkToLogin()
                .setBaseContext(Signup.this);
        linkToLoginHandler.setCommand(linkToLoginCommand);

        CommandHandler signupHandler = new CommandHandler(GlobalCommandID.SIGNUP_BUTTON_COMMAND);
        Command signupCommand = new SignupButtonCommand()
                .setBaseContext(Signup.this)
                .setEmail(email).setPassword(password).setConfirmPassword(confirmPassword)
                .setPhone(phone).setName(name)
                .setSuccessfulReaction(successfulSignupReaction).setFailureReaction(failureSignupReaction);
        signupHandler.setCommand(signupCommand);
        signupHandler.setNext(linkToLoginHandler);

        commandHandler = signupHandler;
    }

    private Runnable successfulSignupReaction = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(Signup.this, SignupConstants.SIGN_UP_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private Runnable failureSignupReaction = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(Signup.this, SignupConstants.SIGN_UP_FAILED, Toast.LENGTH_LONG).show();
        }
    };

}
