package com.skel.appskeletonv4.domain.common;

import android.app.Application;

import androidx.room.Room;

import com.skel.appskeletonv4.data.localdb.AppDatabase;
import com.skel.appskeletonv4.data.remotedb.api.ApiGenerator;
import com.skel.appskeletonv4.data.remotedb.api.ApiService;
import com.skel.appskeletonv4.data.repository.branch.BranchRepository;
import com.skel.appskeletonv4.data.repository.plans.PackageRepository;

public class CoreApp extends Application {

    private AppDatabase appDatabase;

    public AppDatabase getLocalDb(){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(this, AppDatabase.class, Config.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    public ApiService getWebService(){
        ApiService apiService = ApiGenerator.createApiService(ApiService.class, Config.ENCRYPTED_API_KEY);
        return apiService;
    }

    public AppExecutors getAppExecutors(){
        AppExecutors appExecutors = new AppExecutors();
        return appExecutors;
    }

    public BranchRepository getBranchRepository(){
        return new BranchRepository(getWebService(),getLocalDb(),getAppExecutors());
    }

    public PackageRepository getPackageRepository(){
        return new PackageRepository(getWebService(),getLocalDb(),getAppExecutors());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        appDatabase = null;
    }
}
