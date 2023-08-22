package com.example.call_center_driver_app.main_fragments.notification;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.call_center_driver_app.MainActivity;

public class NotificationFragment extends Fragment
{
    //ensure thread-safe Singleton with lazy load
    private static volatile NotificationFragment instance;
    private static final String INIT_NAME = "NOTIFICATION_FRAGMENT";
    private static String name;
    private Context context;
    private MainActivity mainActivity;


    NotificationFragment()
    {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public NotificationFragment newInstance(String fragment_name)
    {
        NotificationFragment tempInstance = instance;
        if(tempInstance != null)
        {
            return tempInstance;
        }
        synchronized (NotificationFragment.this)
        {
            if(instance == null)
            {
                instance = new NotificationFragment();
                Bundle initBundle = new Bundle();
                initBundle.putString(INIT_NAME, fragment_name);
                instance.setArguments(initBundle);
            }
            return instance;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            name = getArguments().getString(INIT_NAME);
        }
        try
        {
            this.context = getActivity();
            mainActivity = (MainActivity) getActivity();
        }
        catch(IllegalStateException ex)
        {
            throw new IllegalStateException("Notification:onCreate: IllegalStateException");
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        //TODO: binding to component of notification page
        return null;
    }
}
