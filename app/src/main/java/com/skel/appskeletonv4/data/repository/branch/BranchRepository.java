package com.skel.appskeletonv4.data.repository.branch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.skel.appskeletonv4.data.localdb.AppDatabase;
import com.skel.appskeletonv4.data.remotedb.api.ApiGenerator;
import com.skel.appskeletonv4.data.remotedb.api.ApiService;
import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.BranchProfileData;
import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.GetBranchProfileDataResponse;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.data.repository.common.SharedRepository;
import com.skel.appskeletonv4.domain.common.AppExecutors;
import com.skel.appskeletonv4.domain.common.utils.api_util.ApiResponse;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.domain.repository.IBranchRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BranchRepository extends SharedRepository  implements IBranchRepository {

    public BranchRepository(ApiService webService, AppDatabase localDb, AppExecutors appExecutors) {
        super(webService, localDb, appExecutors);
    }


    /**
     * change 000028
     * check api call portion
     * */
    @Override
    public LiveData<Resource<BranchLoginDTO>> branchLogin(String email, String password) {
        MediatorLiveData<Resource<BranchLoginDTO>> statusLiveData = new MediatorLiveData<>();

        appExecutors.networkIO().execute(()->{
            try{
                Response<BranchLoginDTO> response = webService.branchLogin(email, password).execute();
                ApiResponse<BranchLoginDTO> apiResponse = new ApiResponse<>(response);

                if(apiResponse.isSuccessful()){

                    BranchLoginDTO branchLoginResponse = apiResponse.body;
                    if(branchLoginResponse!=null){
                        if(branchLoginResponse.getStatus()){

                            appExecutors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        localDb.branchDao().deleteBranchLogin();

                                        localDb.branchDao().saveBranchLogin(branchLoginResponse.getData());


                                        statusLiveData.postValue(Resource.success(apiResponse.body));

                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }else {
                            statusLiveData.postValue(Resource.error(branchLoginResponse.getMessage(), null));
                        }
                    }else {
                        statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                    }

                }else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }
            }catch (Exception e){
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }
        });

        return statusLiveData;
    }

    @Override
    public LiveData<List<Branch>> getSavedBranchLogin() {
        return localDb.branchDao().getAllBranchLogin();
    }

    @Override
    public LiveData<Resource<Boolean>> deleteBranchLogin() {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {

                    localDb.branchDao().deleteBranchLogin();
                    statusLiveData.postValue(Resource.success(true));

                }catch (Exception e){
                    e.printStackTrace();
                    statusLiveData.postValue(Resource.error(e.getMessage(),false));
                }
            }
        });
        return statusLiveData;
    }

    @Override
    public LiveData<Resource<BranchProfileData>> getBranchProfileData(String branchId) {
        MediatorLiveData<Resource<BranchProfileData>> statusLiveData = new MediatorLiveData<>();

        appExecutors.networkIO().execute(()->{
            try{

                Response<GetBranchProfileDataResponse> response = webService.getBranchProfileData(branchId).execute();
                ApiResponse<GetBranchProfileDataResponse> apiResponse = new ApiResponse<>(response);

                if(apiResponse.isSuccessful()){

                    GetBranchProfileDataResponse branchProfileDataResponse = apiResponse.body;
                    if(branchProfileDataResponse!=null){
                        if(branchProfileDataResponse.getSuccess()){
                            statusLiveData.postValue(Resource.success(branchProfileDataResponse.getData()));
                        }else {
                            statusLiveData.postValue(Resource.error(branchProfileDataResponse.getMessage(), null));
                        }
                    }else {
                        statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                    }

                }else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }
            }catch (Exception e){
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }
        });

        return statusLiveData;
    }
}
