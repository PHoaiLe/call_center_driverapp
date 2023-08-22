package com.example.call_center_driver_app.activities.signup.commands;

import android.content.Context;
import android.widget.Toast;

import com.example.call_center_driver_app.GlobalResources;
import com.example.call_center_driver_app.activities.signup.Signup;
import com.example.call_center_driver_app.activities.signup.constants.SignupConstants;
import com.example.call_center_driver_app.components.MyDriver;
import com.example.call_center_driver_app.my_interfaces.Command;
import com.example.call_center_driver_app.repositories.firebase.FirebaseRepository;
import com.google.android.material.textfield.TextInputEditText;

public class SignupButtonCommand implements Command {

    private TextInputEditText email, password, confirmPassword, phone, name;
    private Context baseContext;

    private Runnable successfulReaction;
    private Runnable failureReaction;

    public SignupButtonCommand()
    {
        email = null;
        password = null;
        phone = null;
        name = null;
        successfulReaction = null;
        failureReaction = null;
    }

    public SignupButtonCommand setEmail(TextInputEditText email)
    {
        this.email = email;
        return this;
    }

    public SignupButtonCommand setPassword(TextInputEditText password)
    {
        this.password = password;
        return this;
    }

    public SignupButtonCommand setPhone(TextInputEditText phone)
    {
        this.phone = phone;
        return this;
    }

    public SignupButtonCommand setName(TextInputEditText name)
    {
        this.name = name;
        return this;
    }

    public SignupButtonCommand setConfirmPassword(TextInputEditText confirmPassword)
    {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public SignupButtonCommand setBaseContext(Context context)
    {
        this.baseContext = context;
        return this;
    }

    public SignupButtonCommand setSuccessfulReaction(Runnable reaction)
    {
        successfulReaction = reaction;
        return this;
    }

    public SignupButtonCommand setFailureReaction(Runnable reaction)
    {
        failureReaction = reaction;
        return this;
    }

    @Override
    public void execute() {
        if(baseContext == null)
        {
            throw new NullPointerException("Provide context of the activity");
        }
        if(email == null)
        {
            throw new NullPointerException("Provide reference to email field (use setEmail)");
        }
        if(password == null)
        {
            throw new NullPointerException("Provide reference to password field (use setPassword)");
        }
        if(confirmPassword == null)
        {
            throw new NullPointerException("Provide reference to confirm password field (use setConfirmPassword)");
        }
        if(phone == null)
        {
            throw new NullPointerException("Provide reference to phone field (use setPhone)");
        }
        if(name == null)
        {
            throw new NullPointerException("Provide reference to name field (use setPhone)");
        }

        String emailStr, passwordStr, confirmPasswordStr, phoneStr, nameStr;
        emailStr = email.getText().toString();
        passwordStr = password.getText().toString();
        confirmPasswordStr = confirmPassword.getText().toString();
        phoneStr = phone.getText().toString();
        nameStr = name.getText().toString();
        if(emailStr.isEmpty())
        {
            Toast.makeText(baseContext, SignupConstants.EMPTY_EMAIL, Toast.LENGTH_LONG).show();
            return;
        }
        if(passwordStr.isEmpty())
        {
            Toast.makeText(baseContext, SignupConstants.EMPTY_PASSWORD, Toast.LENGTH_LONG).show();
            return;
        }
        if(passwordStr.length() < 8)
        {
            Toast.makeText(baseContext, SignupConstants.INVALID_PASSWORD_LENGTH, Toast.LENGTH_LONG).show();
            return;
        }
        if(confirmPasswordStr.isEmpty())
        {
            Toast.makeText(baseContext, SignupConstants.EMPTY_PASSWORD_CONFIRM, Toast.LENGTH_LONG).show();
            return;
        }
        if(confirmPasswordStr.equals(passwordStr) == false)
        {
            Toast.makeText(baseContext, SignupConstants.INVALID_PASSWORD_CONFIRM, Toast.LENGTH_LONG).show();
            return;
        }
        if(phoneStr.isEmpty())
        {
            Toast.makeText(baseContext, SignupConstants.EMPTY_PHONE, Toast.LENGTH_SHORT).show();
            return;
        }

        MyDriver driverInfo = new MyDriver();
        driverInfo.setEmail(emailStr);
        driverInfo.setName(nameStr);
        driverInfo.setPhoneNumber(phoneStr);
        FirebaseRepository firebaseRepository =  ((GlobalResources) ((Signup) baseContext).getApplication()).getFirebaseRepository();
        firebaseRepository.signupByEmailAndPassword(emailStr, passwordStr, driverInfo, successfulReaction, failureReaction);

    }
}
