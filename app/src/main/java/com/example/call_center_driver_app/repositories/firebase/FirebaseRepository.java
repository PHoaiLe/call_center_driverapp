package com.example.call_center_driver_app.repositories.firebase;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.call_center_driver_app.R;
import com.example.call_center_driver_app.components.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

@Singleton
public class FirebaseRepository {

    private static volatile FirebaseRepository firebaseRepository;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private Context appContext;
    private static Handler handler;

//    public void setAppContext(Context providedContext)
//    {
//        appContext = providedContext;
//    }

    private FirebaseRepository(Context providedContext)
    {
        try
        {
            appContext = providedContext;
            FirebaseApp.initializeApp(appContext);
            Log.e("FirebaseApp: ", FirebaseApp.getInstance().getName());
            firebaseAuth = FirebaseAuth.getInstance();
            firestore = FirebaseFirestore.getInstance();
            handler = new Handler();


        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public static FirebaseRepository getInstance(Context providedContext)
    {
        FirebaseRepository tempAccess = firebaseRepository;
        if(tempAccess != null)
        {
            System.out.println("FirebaseRepository");
            return tempAccess;
        }
        else
        {
            if(firebaseRepository == null)
            {
                firebaseRepository = new FirebaseRepository(providedContext);
            }
            System.out.println("FirebaseRepository");
            return firebaseRepository;
        }
    }

    public FirebaseUser getCurrentUser()
    {
        return firebaseAuth.getCurrentUser();
    }

    public FirebaseAuth getFirebaseAuth()
    {
        return firebaseAuth;
    }

    public void loginByEmailAndPassword(@NotNull String email,@NotNull String password, Runnable successfulReaction, Runnable failureReaction)
    {
        if(firebaseAuth == null)
        {
            throw new NullPointerException("LOGIN: FirebaseAuth is null");
        }
        if(firebaseAuth.getCurrentUser() != null)
        {
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(successfulReaction != null)
                        {
                            handler.post(successfulReaction);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(failureReaction != null)
                        {
                            handler.post(failureReaction);
                        }
                    }
                });
    }

    public void signupByEmailAndPassword(@NotNull String email, @NotNull String password,@NotNull User userInfo, Runnable successfulReaction, Runnable failureReaction)
    {
        if(firebaseAuth == null)
        {
            throw new NullPointerException("SIGNUP: FirebaseAuth is null");
        }
        if(firebaseAuth.getCurrentUser() != null)
        {
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        firestore.collection(appContext.getResources().getString(R.string.DATABASE_ACCESS_DRIVER_INFO_COLLECTION))
                                .document(authResult.getUser().getUid())
                                .set(userInfo);
                        if(successfulReaction != null)
                        {
                            handler.post(successfulReaction);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(failureReaction != null)
                        {
                            handler.post(failureReaction);
                        }
                    }
                });
    }

}
