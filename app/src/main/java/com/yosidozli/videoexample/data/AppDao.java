package com.yosidozli.videoexample.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.yosidozli.videoexample.data.model.FavoriteRef;
import com.yosidozli.videoexample.data.model.User;
import com.yosidozli.videoexample.data.model.UserWithVideos;
import com.yosidozli.videoexample.data.model.VideoEntity;

import java.util.List;

@Dao
public interface  AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertFavorite(FavoriteRef favoriteRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertVideo(VideoEntity videos);

    @Query("SELECT * FROM users WHERE userId = :userId AND password = :password")
    public LiveData<User> getUser(String userId, String password);

    @Transaction
    @Query("SELECT * FROM videos INNER JOIN users WHERE userId = :userId ")
    public List<UserWithVideos> getFavouritesVideos(String userId);

    @Query("SELECT * FROM videos")
    public LiveData< List<VideoEntity>> getAllVideos();

}
