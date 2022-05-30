package com.example.mmvm_room_databiding.data.database;

import androidx.room.Database;

import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;

@Database(entities = {WorkEntity.class}, version = 1)
public abstract class AppDatabase {

}
