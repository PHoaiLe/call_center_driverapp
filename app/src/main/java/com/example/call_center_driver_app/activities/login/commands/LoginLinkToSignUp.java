package com.example.call_center_driver_app.activities.login.commands;

import android.content.Context;
import android.content.Intent;

import com.example.call_center_driver_app.activities.login.Login;
import com.example.call_center_driver_app.activities.signup.Signup;
import com.example.call_center_driver_app.my_interfaces.Command;

public class LoginLinkToSignUp implements Command {

    private Context baseContext;

    public LoginLinkToSignUp()
    {
        baseContext = null;
    }

    public LoginLinkToSignUp setBaseContext(Context context)
    {
        baseContext = context;
        return this;
    }

    @Override
    public void execute() {
        if(baseContext == null)
        {
            return;
        }
        Intent signupIntent = new Intent(baseContext, Signup.class);
        baseContext.startActivity(signupIntent);
        ((Login) baseContext).finish();
    }
}
