package com.skel.appskeletonv4.data.localdb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skel.appskeletonv4.data.localdb.entity.AboutUsEntity;


@Dao
public interface AboutUsDao {

    @Query("SELECT * FROM aboutus_table")
    LiveData<AboutUsEntity> getAboutUs();

    @Insert
    void insetAboutUs(AboutUsEntity abouts);


}
