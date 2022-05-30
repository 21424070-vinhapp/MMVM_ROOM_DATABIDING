package com.example.mmvm_room_databiding.data.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface WorkDao {

    @Query("select * from work")
    Observable<List<WorkEntity>> getListWork();
}
