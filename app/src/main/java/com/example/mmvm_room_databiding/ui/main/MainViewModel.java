package com.example.mmvm_room_databiding.ui.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.data.respositories.WorkRespository;

import java.util.List;

public class MainViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<List<WorkEntity>>  listWork=new MutableLiveData<>();
    private MutableLiveData<Long>  idInsert=new MutableLiveData<>();
    private WorkRespository workRespository;
    private Context context;

    public MainViewModel(Context context)
    {
        this.context=context;
        workRespository=new WorkRespository(context);
    }

    public LiveData<List<WorkEntity>> getListWork(){
        return listWork;
    }

    public LiveData<Long> getIdInsert(){
        return idInsert;
    }

    public void queryListWork(){
        workRespository.getListWork();
    }

    public void queryInsertWork(WorkEntity workEntity)
    {
        workRespository.insertWork(workEntity);
    }
}
