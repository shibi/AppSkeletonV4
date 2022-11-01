package com.skel.appskeletonv4.data.localdb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;

import java.util.List;

@Dao
public interface BranchDao {


    @Insert
    void saveBranchLogin(Branch branchLogin);

    @Query("SELECT * FROM branch_table")
    LiveData<List<Branch>> getAllBranchLogin();

    @Query("DELETE FROM branch_table")
    void deleteBranchLogin();

}
