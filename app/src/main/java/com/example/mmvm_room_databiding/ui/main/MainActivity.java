package com.example.mmvm_room_databiding.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mmvm_room_databiding.R;
import com.example.mmvm_room_databiding.classes.adapter.WorkAdapter;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    private static final String TAG = "BBB";
    WorkAdapter workAdapter;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        workAdapter=new WorkAdapter(this);
        activityMainBinding.recyckerViewWork.setAdapter(workAdapter);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainViewModel(MainActivity.this);
            }
        }).get(MainViewModel.class);

        mainViewModel.getListWork().observe(this, new Observer<List<WorkEntity>>() {
            @Override
            public void onChanged(List<WorkEntity> workEntities) {
                //Log.d("BBB", "Total: " + workEntities.size());
                if(workEntities!=null && workEntities.size()>0)
                {
                    workAdapter.updateListWork(workEntities);
                }
            }
        });

        mainViewModel.getIdInsert().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.d(TAG, "Row: "+aLong);
            }
        });


        //get list work in db
        mainViewModel.queryListWork();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //insert work in db
                WorkEntity workEntity = new WorkEntity("to do 3", "nothing 3");
                mainViewModel.queryInsertWork(workEntity);
            }
        },100);


    }
}