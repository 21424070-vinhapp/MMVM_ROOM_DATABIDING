package com.example.mmvm_room_databiding.data.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "work")
public class WorkEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String message;

    @ColumnInfo(name = "is_done", defaultValue ="0")
    private boolean isDone;

    public WorkEntity() {
    }

    @Ignore
    public WorkEntity(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
