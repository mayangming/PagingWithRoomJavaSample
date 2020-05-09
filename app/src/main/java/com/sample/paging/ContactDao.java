package com.sample.paging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactBean contactBean);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ContactBean> contactBeans);

    @Query("SELECT * FROM contact")
    DataSource.Factory<Integer, ContactBean> queryPageContact();
}