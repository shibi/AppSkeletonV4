package com.skel.appskeletonv4.data.localdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.skel.appskeletonv4.data.localdb.dao.BranchDao;
import com.skel.appskeletonv4.data.localdb.entity.AboutUsEntity;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;


@Database(entities = {AboutUsEntity.class,Branch.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BranchDao branchDao();

}
