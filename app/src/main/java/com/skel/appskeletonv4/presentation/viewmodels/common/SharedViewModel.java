package com.skel.appskeletonv4.presentation.viewmodels.common;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.skel.appskeletonv4.data.localdb.AppDatabase;
import com.skel.appskeletonv4.data.remotedb.api.ApiService;
import com.skel.appskeletonv4.domain.common.AppExecutors;
import com.skel.appskeletonv4.domain.common.CoreApp;

public class SharedViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> loadingState = new MutableLiveData<>();
    private boolean isLoading;

    protected ApiService webService;
    protected AppDatabase localDb;
    protected AppExecutors appExecutors;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        onInitDependencies(application);
    }

    protected void onInitDependencies(Application application){
        this.localDb = ((CoreApp)application).getLocalDb();
        this.webService = ((CoreApp)application).getWebService();
        this.appExecutors = ((CoreApp)application).getAppExecutors();
    }

    public MutableLiveData<Boolean> getLoadingState(){
        return loadingState;
    }

    public void setLoadingState(boolean isLoading){
        this.isLoading = isLoading;
        loadingState.setValue(isLoading);
    }
}
