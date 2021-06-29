package com.yosidozli.videoexample.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey()
    @NonNull
    public String userId;
    public String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }


}
