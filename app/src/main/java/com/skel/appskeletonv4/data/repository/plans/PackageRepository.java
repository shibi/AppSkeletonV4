package com.skel.appskeletonv4.data.repository.plans;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.skel.appskeletonv4.data.localdb.AppDatabase;
import com.skel.appskeletonv4.data.remotedb.api.ApiService;
import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageDTO;
import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageResponseDTO;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceItem;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceListResponse;
import com.skel.appskeletonv4.data.repository.common.SharedRepository;
import com.skel.appskeletonv4.domain.common.AppExecutors;
import com.skel.appskeletonv4.domain.common.utils.api_util.ApiResponse;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.domain.repository.IPackageRepository;

import java.util.List;

import retrofit2.Response;

public class PackageRepository extends SharedRepository implements IPackageRepository {


    public PackageRepository(ApiService webService, AppDatabase localDb, AppExecutors appExecutors) {
        super(webService, localDb, appExecutors);
    }

    @Override
    public LiveData<Resource<List<PackageDTO>>> getAllPackages() {

        MediatorLiveData<Resource<List<PackageDTO>>> statusLiveData = new MediatorLiveData<>();

        appExecutors.networkIO().execute(()->{
            try{

                Response<PackageResponseDTO> response = webService.getAllPackages().execute();
                ApiResponse<PackageResponseDTO> apiResponse = new ApiResponse<>(response);

                if(apiResponse.isSuccessful()){

                    if(apiResponse.body!=null && apiResponse.body.getSuccess()){

                        if(apiResponse.body.getSuccess()) {
                            statusLiveData.postValue(Resource.success(apiResponse.body.getData()));
                        }else{
                            statusLiveData.postValue(Resource.error(apiResponse.body.getMessage(), null));
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
    public LiveData<Resource<List<ServiceItem>>> getAllServices() {

        MediatorLiveData<Resource<List<ServiceItem>>> statusLiveData = new MediatorLiveData<>();

        appExecutors.networkIO().execute(()->{
            try{

                Response<ServiceListResponse> response = webService.getAllServices().execute();
                ApiResponse<ServiceListResponse> apiResponse = new ApiResponse<>(response);

                if(apiResponse.isSuccessful()){

                    if(apiResponse.body!=null){

                        if(apiResponse.body.getSuccess()) {
                            statusLiveData.postValue(Resource.success(apiResponse.body.getData()));
                        }else{
                            statusLiveData.postValue(Resource.error(apiResponse.body.getMessage(), null));
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
