package com.example.mmvm_room_databiding.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mmvm_room_databiding.R;
import com.example.mmvm_room_databiding.classes.Dialog.AppDialog;
import com.example.mmvm_room_databiding.classes.adapter.WorkAdapter;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.databinding.ActivityMainBinding;
import com.example.mmvm_room_databiding.databinding.DialogUpdateWorkBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    private static final String TAG = "BBB";
    WorkAdapter workAdapter;
    ActivityMainBinding activityMainBinding;
    private WorkEntity workEntity;
    private DialogUpdateWorkBinding dialogUpdateWorkBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainViewModel(MainActivity.this);
            }
        }).get(MainViewModel.class);

        workAdapter = new WorkAdapter(this);
        activityMainBinding.recyckerViewWork.setAdapter(workAdapter);

        mainViewModel.getListWork().observe(this, new Observer<List<WorkEntity>>() {
            @Override
            public void onChanged(List<WorkEntity> workEntities) {
                //Log.d("BBB", "Total: " + workEntities.size());
                if (workEntities != null && workEntities.size() > 0) {
                    workAdapter.updateListWork(workEntities);
                }
            }
        });

//        mainViewModel.getIdInsert().observe(this, new Observer<Long>() {
//            @Override
//            public void onChanged(Long aLong) {
//                Log.d(TAG, "Row: " + aLong);
//            }
//        });


        //get list work in db
        mainViewModel.queryListWork();

        workAdapter.setOnItemClickListener(new WorkAdapter.OnListenClickItem() {
            @Override
            public void onClickDelete(int position) {
                AppDialog.createDialogDelete(MainActivity.this, new AppDialog.onListenClickDelete() {
                    @Override
                    public void onDelete() {
                        mainViewModel.queryDeleteWork(workAdapter.getLstWork().get(position));
                    }
                });
            }

            @Override
            public void onClickUpdate(int position) {
                workEntity=workAdapter.getLstWork().get(position);

                //Log.d(TAG, "onClickUpdate: "+workEntity.getTitle());
                AppDialog.createDialogUpdate(MainActivity.this, new AppDialog.onListenClickUpdate() {

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onUpdate(String title, String message) {
                        //Log.d(TAG, "onUpdate: "+title);
                        if (title.isEmpty() && message.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        workEntity.setTitle(title);
                        workEntity.setMessage(message);
                        mainViewModel.queryUpdateWork(workEntity);
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.menu_main,menu);
//        return true;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create:
                AppDialog.createDialog(this, new AppDialog.onListenClickDialog() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSave(String title, String message) {
                        if (title.isEmpty() && message.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mainViewModel.queryInsertWork(new WorkEntity(title, message));
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}