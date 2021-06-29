package com.yosidozli.videoexample.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yosidozli.videoexample.R;
import com.yosidozli.videoexample.data.model.FavoriteRef;
import com.yosidozli.videoexample.data.model.User;
import com.yosidozli.videoexample.data.model.VideoEntity;

import java.util.UUID;
import java.util.concurrent.Executors;

@Database(entities = {User.class, VideoEntity.class, FavoriteRef.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            public void onCreate (SupportSQLiteDatabase db) {
                Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            AppDao appDao = getInstance(context).appDao();
                            appDao.insertVideo( new VideoEntity(UUID.randomUUID().toString(),
                                    R.raw.a,"a"));
                            appDao.insertVideo( new VideoEntity(UUID.randomUUID().toString(),
                                    R.raw.b,"b"));
                            appDao.insertVideo(new VideoEntity(UUID.randomUUID().toString(),
                                    R.raw.c,"c"));
                            appDao.insertVideo(new VideoEntity(UUID.randomUUID().toString(),
                                    R.raw.d,"d"));
                }
                );


            }


            public void onOpen (SupportSQLiteDatabase db) {  }
        };

        if (INSTANCE == null)
            INSTANCE = Room
                    .databaseBuilder(context, AppDatabase.class, "db")
                    .addCallback(rdc)
                    .build();
        return INSTANCE;
    }


    public abstract AppDao appDao();

}