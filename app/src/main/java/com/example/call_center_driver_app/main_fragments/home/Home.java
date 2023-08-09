package com.example.call_center_driver_app.main_fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.call_center_driver_app.MainActivity;
import com.example.call_center_driver_app.R;
import com.google.android.material.textview.MaterialTextView;

import javax.inject.Singleton;

@Singleton
public class Home extends Fragment {

    //ensure thread-safe Singleton with lazy load
    private static volatile Home instance;
    private static String INIT_NAME = "HOME";

    private static String name;

    private MainActivity mainActivity;

    private Context context;

    //components

    private LinearLayoutCompat handlingTaskBlock;
    private MaterialTextView handlingTaskCustomerName;
    private MaterialTextView handlingTaskStart;
    private MaterialTextView handlingTaskDest;
    private MaterialTextView handlingTaskTotal;
    private MaterialTextView handlingTaskSchedule;
    private MaterialTextView handlingTaskDistance;
    private RecyclerView availableTaskRecyclerView;


    Home()
    {
        //require an empty constructor
    }

    public void setContext(Context providedContext)
    {
        context = providedContext;
    }

    public void setName(String providedName)
    {
        name = providedName;
    }

    public static Home newInstance(String fragmentName)
    {
        Home tempInstance = instance;
        if(tempInstance != null)
        {
            return tempInstance;
        }
        //else
        synchronized (Home.class)
        {
            if(instance == null)
            {
                instance = new Home();
                Bundle initBundle = new Bundle();
                initBundle.putString(INIT_NAME, fragmentName);
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
            Bundle initBundle = getArguments();
            name = initBundle.getString(INIT_NAME);
        }
        try
        {
            context = getActivity();
            mainActivity = (MainActivity) getActivity();
        }
        catch (IllegalStateException ex)
        {
            throw new IllegalStateException("Home:onCreate: IllegalStateException");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View homeView = inflater.inflate(R.layout.fragment_home, null);

        handlingTaskBlock = homeView.findViewById(R.id.home_handling_task_block);
        handlingTaskCustomerName = homeView.findViewById(R.id.home_handling_task_customername);
        handlingTaskStart = homeView.findViewById(R.id.home_handling_task_start);
        handlingTaskDest = homeView.findViewById(R.id.home_handling_task_destination);
        handlingTaskSchedule = homeView.findViewById(R.id.home_handling_task_schedule);
        handlingTaskDistance = homeView.findViewById(R.id.home_handling_task_distance);

        //recyclerView
        availableTaskRecyclerView = homeView.findViewById(R.id.home_available_tasks_recyclerview);

        return homeView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
