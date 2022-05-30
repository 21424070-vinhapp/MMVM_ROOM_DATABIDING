package com.example.mmvm_room_databiding.data.respositories;

import android.content.Context;

import com.example.mmvm_room_databiding.data.database.AppDatabase;
import com.example.mmvm_room_databiding.data.database.WorkDao;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class WorkRespository {
    private WorkDao workDao;

    public WorkRespository (Context context)
    {
        workDao=AppDatabase.getInstance(context).workDao();
    }

    public Observable<List<WorkEntity>> getListWork()
    {
        return workDao.getListWork();
    }

}
