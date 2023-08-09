package com.example.call_center_driver_app.activities.login.commands;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.call_center_driver_app.GlobalResources;
import com.example.call_center_driver_app.activities.login.Login;
import com.example.call_center_driver_app.activities.login.constants.LoginConstants;
import com.example.call_center_driver_app.my_interfaces.Command;
import com.example.call_center_driver_app.repositories.FirebaseRepository;
import com.google.android.material.textfield.TextInputEditText;

public class LoginButtonCommand implements Command {

    private TextInputEditText account, password;
    private Runnable successfulReaction, failureReaction;
    private Context baseContext;


    public LoginButtonCommand()
    {
        account = null;
        password = null;
        successfulReaction = null;
        failureReaction = null;
        baseContext = null;
    }

    public LoginButtonCommand(Context context)
    {
        this.account = null;
        this.password = null;
        this.successfulReaction = null;
        this.failureReaction = null;
        this.baseContext = context;
    }

    public LoginButtonCommand setAccount(TextInputEditText account)
    {
        this.account = account;
        return this;
    }

    public LoginButtonCommand setPassword(TextInputEditText password)
    {
        this.password = password;
        return this;
    }

    public LoginButtonCommand setSuccessfulReaction(Runnable successfulReaction) {
        this.successfulReaction = successfulReaction;
        return this;
    }

    public LoginButtonCommand setFailureReaction(Runnable failureReaction) {
        this.failureReaction = failureReaction;
        return this;
    }

    public LoginButtonCommand setBaseContext(Context context) {
        this.baseContext = context;
        return this;
    }

    @Override
    public void execute() {
        Log.i("LoginCommand: ", "email: "+ account + "pass: "+ password);
        if(baseContext == null)
        {
            throw new NullPointerException("Provide context of the activity");
        }
        if(account == null)
        {
            throw new NullPointerException("Provide reference to account field (use setAccount)");
        }
        if(password == null)
        {
            throw new NullPointerException("Provide reference to password field (use setPassword)");
        }
        if(account.getText().toString().isEmpty())
        {
            Toast.makeText(baseContext, LoginConstants.EMPTY_ACCOUNT, Toast.LENGTH_LONG).show();
            return;
        }
        if(password.getText().toString().isEmpty())
        {
            Toast.makeText(baseContext, LoginConstants.EMPTY_PASSWORD, Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseRepository firebaseRepository = ((GlobalResources) ((Login) baseContext).getApplication()).getFirebaseRepository();
        firebaseRepository.loginByEmailAndPassword(account.getText().toString(), password.getText().toString(), successfulReaction, failureReaction);
    }
}
