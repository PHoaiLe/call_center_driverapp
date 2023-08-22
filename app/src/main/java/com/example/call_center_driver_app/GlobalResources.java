package com.example.call_center_driver_app;

import android.app.Application;

import com.example.call_center_driver_app.repositories.call_center_server.CallCenterRepository;
import com.example.call_center_driver_app.repositories.firebase.FirebaseRepository;

import javax.inject.Singleton;

//By following this approach, you can create a single instance of your repository that exists throughout the app's lifetime,
// and you can access it from any component without the need to create additional instances or pass references between components.
// It provides a convenient and centralized way to manage application-wide data and services.
// However, keep in mind that managing global state should be done thoughtfully to avoid potential pitfalls
// like memory leaks or overuse of global variables.
//when Repository class extends Application, it will be a singleton class that can be globally
//accessed from any component in our application by calling
// GlobalResources repo = (GlobalResources) getApplication();
@Singleton
public class GlobalResources extends Application
{
    @Singleton
    private FirebaseRepository firebaseRepository;
    @Singleton
    private CallCenterRepository callCenterRepository;


    @Override
    public void onCreate()
    {
        super.onCreate();
        firebaseRepository = FirebaseRepository.getInstance(getApplicationContext());
    }

    public FirebaseRepository getFirebaseRepository()
    {
        return firebaseRepository;
    }

}
