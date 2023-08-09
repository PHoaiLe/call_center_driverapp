package com.example.call_center_driver_app.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.call_center_driver_app.GlobalResources;
import com.example.call_center_driver_app.MainActivity;
import com.example.call_center_driver_app.R;
import com.example.call_center_driver_app.activities.login.commands.LoginButtonCommand;
import com.example.call_center_driver_app.activities.login.commands.LoginLinkToSignUp;
import com.example.call_center_driver_app.activities.login.constants.LoginConstants;
import com.example.call_center_driver_app.constants.GlobalCommandID;
import com.example.call_center_driver_app.my_interfaces.ChainHandler;
import com.example.call_center_driver_app.my_interfaces.Command;
import com.example.call_center_driver_app.other_components.CommandHandler;
import com.example.call_center_driver_app.repositories.FirebaseRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.GetTokenResult;


public class Login extends AppCompatActivity {

    private TextInputEditText account, password;
    private MaterialButton loginButton, linkToSignUp;
    private ChainHandler chainOfCommandHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = findViewById(R.id.login_account_input);
        password = findViewById(R.id.login_password_input);
        loginButton = findViewById(R.id.login_login_button);
        linkToSignUp = findViewById(R.id.login_goto_signup_link);

        initCommandsHandler();
        linkToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chainOfCommandHandler.handle(GlobalCommandID.LINK_TO_SIGNUP);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chainOfCommandHandler.handle(GlobalCommandID.LOGIN_COMMAND);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }



    private void initCommandsHandler()
    {
        CommandHandler linkToSignupHandler = new CommandHandler(GlobalCommandID.LINK_TO_SIGNUP);
        Command linkToSignUpCommand = new LoginLinkToSignUp().setBaseContext(Login.this);
        linkToSignupHandler.setCommand(linkToSignUpCommand);

        CommandHandler loginHandler = new CommandHandler(GlobalCommandID.LOGIN_COMMAND);
        Command loginCommand = new LoginButtonCommand(Login.this)
                .setAccount(account)
                .setPassword(password)
                .setSuccessfulReaction(successfulLoginReaction)
                .setFailureReaction(failureLoginReaction);
        loginHandler.setCommand(loginCommand);
        loginHandler.setNext(linkToSignupHandler);

        //assign ChainHandler to chainOfCommandHandler variable
        chainOfCommandHandler = loginHandler;
    }

    private Runnable successfulLoginReaction = new Runnable() {
        @Override
        public void run()
        {
            Toast.makeText(Login.this, LoginConstants.LOGIN_SUCCESSFULLY, Toast.LENGTH_LONG).show();
            finish();
        }
    };

    private Runnable failureLoginReaction = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(Login.this, LoginConstants.CANNOT_LOGIN, Toast.LENGTH_LONG).show();
        }
    };

}