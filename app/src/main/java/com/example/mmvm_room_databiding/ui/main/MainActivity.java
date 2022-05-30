package com.example.mmvm_room_databiding.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.mmvm_room_databiding.R;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mainViewModel=new ViewModelProvider(this, new ViewModelProvider.Factory() {
           @NonNull
           @Override
           public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
               return (T) new MainViewModel(MainActivity.this);
           }
       }).get(MainViewModel.class);

       mainViewModel.getListWork().observe(this, new Observer<List<WorkEntity>>() {
           @Override
           public void onChanged(List<WorkEntity> workEntities) {
               Log.d("BBB", "Total: "+workEntities.size());
           }
       });

       mainViewModel.queryListWork();
    }
}