package com.example.call_center_driver_app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.call_center_driver_app.activities.login.Login;
import com.example.call_center_driver_app.main_fragments.home.Home;
import com.example.call_center_driver_app.repositories.firebase.FirebaseRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity {

    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNavigationView;
    private Fragment currentScreen;


    private String HOME_INIT_NAME = "HOME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_nav);

        currentScreen = Home.newInstance(HOME_INIT_NAME);
        fragmentTransaction.replace(R.id.main_where_place_fragment, currentScreen);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseRepository firebaseRepository = ((GlobalResources) getApplication()).getFirebaseRepository();
        if(firebaseRepository.getCurrentUser() == null)
        {
            Intent startLoginIntent = new Intent(MainActivity.this, Login.class);
            startActivity(startLoginIntent);
            //finish();
        }
    }
}