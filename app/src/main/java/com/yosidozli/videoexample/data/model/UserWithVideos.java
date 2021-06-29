package com.yosidozli.videoexample.data.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserWithVideos {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "id",
            associateBy = @Junction(FavoriteRef.class)
    )
    public List<VideoEntity> favorites;
}
