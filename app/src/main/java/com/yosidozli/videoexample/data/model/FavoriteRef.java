package com.yosidozli.videoexample.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "id"})
public class FavoriteRef {
    @NonNull
    public String userId;
    @NonNull
    public String id;

    public FavoriteRef(String userId, String videoId) {
        this.userId = userId;
        this.id = videoId;
    }

    public FavoriteRef() {
    }
}
