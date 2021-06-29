package com.yosidozli.videoexample.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "videos")
public class VideoEntity {
    @PrimaryKey
    @NonNull
    public String id;
    public int resource;
    public String name;

    public VideoEntity(String id, int resource, String name) {
        this.id = id;
        this.resource = resource;
        this.name = name;
    }


}
