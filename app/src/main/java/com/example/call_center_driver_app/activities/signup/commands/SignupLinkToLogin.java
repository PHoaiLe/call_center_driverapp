package com.example.call_center_driver_app.activities.signup.commands;

import android.content.Context;
import android.content.Intent;

import com.example.call_center_driver_app.activities.login.Login;
import com.example.call_center_driver_app.activities.signup.Signup;
import com.example.call_center_driver_app.my_interfaces.Command;

public class SignupLinkToLogin implements Command {

    private Context baseContext;

    public SignupLinkToLogin()
    {
        baseContext = null;
    }

    public SignupLinkToLogin(Context context)
    {
        baseContext = context;
    }

    public SignupLinkToLogin setBaseContext(Context context)
    {
        baseContext = context;
        return this;
    }

    @Override
    public void execute() {
        Intent loginIntent = new Intent(baseContext, Login.class);
        baseContext.startActivity(loginIntent);
        ((Signup) baseContext).finish();
    }
}
