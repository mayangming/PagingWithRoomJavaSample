package com.sample.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 字段数据库
 */

@Database(entities = {
        ContactBean.class
        },version = 1,exportSchema = false)
public abstract class ConcatDataBase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    private static volatile ConcatDataBase INSTANCE;
    private static final int NUMBERS_THREAD = 4;

    public static final ExecutorService dataBaseExecutorService = Executors.newFixedThreadPool(NUMBERS_THREAD);



    public static ConcatDataBase getDatabase(final Context context){
        if (null == INSTANCE){
            synchronized (ConcatDataBase.class){
                if (null == INSTANCE){
                    INSTANCE = Room.databaseBuilder(context,ConcatDataBase.class,"contact").build();
                }
            }
        }
        return INSTANCE;
    }
}