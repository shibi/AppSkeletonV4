package com.skel.appskeletonv4.presentation.viewmodels.plans;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageDTO;
import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceItem;
import com.skel.appskeletonv4.domain.common.CoreApp;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.domain.repository.IPackageRepository;
import com.skel.appskeletonv4.presentation.viewmodels.common.SharedViewModel;

import java.util.List;

public class PackagesViewModel extends SharedViewModel {

    //repository
    private IPackageRepository packageRepository;

    //all package
    private LiveData<Resource<List<PackageDTO>>> allPackageData;
    private MutableLiveData<Boolean> allPackageObj = new MutableLiveData<>();

    //all services
    private LiveData<Resource<List<ServiceItem>>> getAllServiceData;
    private MutableLiveData<Boolean> getAllServiceObj = new MutableLiveData<>();


    public PackagesViewModel(@NonNull Application application) {
        super(application);

        //repository
        packageRepository = ((CoreApp)application).getPackageRepository();


        //all package data
        allPackageData = Transformations.switchMap(allPackageObj, isLoad->{
            if(!isLoad){
                return new MutableLiveData<>();
            }
            return packageRepository.getAllPackages();
        });


        //Get all services
        getAllServiceData = Transformations.switchMap(getAllServiceObj, isLoad->{
            if(isLoad == false){
                return new MutableLiveData<>();
            }
            return packageRepository.getAllServices();
        });

    }





    //get all package data
    public LiveData<Resource<List<PackageDTO>>> getAllPackageData(){
        return allPackageData;
    }
    public void setAllPackageObj(Boolean isLoad){
        allPackageObj.postValue(isLoad);
    }


    //Get all service
    public LiveData<Resource<List<ServiceItem>>> getGetAllServiceData(){
        return getAllServiceData;
    }
    public void setGetAllServiceObj(Boolean isLoad){
        getAllServiceObj.postValue(isLoad);
    }


}
