package com.skel.appskeletonv4.data.repository.common;

import com.skel.appskeletonv4.data.localdb.AppDatabase;
import com.skel.appskeletonv4.data.remotedb.api.ApiService;
import com.skel.appskeletonv4.domain.common.AppExecutors;

public class SharedRepository {

    protected ApiService webService;
    protected AppDatabase localDb;
    protected AppExecutors appExecutors;

    public SharedRepository(ApiService webService, AppDatabase localDb, AppExecutors appExecutors) {
        this.webService = webService;
        this.localDb = localDb;
        this.appExecutors = appExecutors;
    }
}
