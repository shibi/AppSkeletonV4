package com.skel.appskeletonv4.presentation.viewmodels.branch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.BranchProfileData;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.domain.common.CoreApp;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.domain.repository.IBranchRepository;
import com.skel.appskeletonv4.presentation.viewmodels.common.SharedViewModel;

import java.util.List;

public class BranchViewModel extends SharedViewModel {

    private IBranchRepository branchRepository;


    //Branch Login
    private LiveData<Resource<BranchLoginDTO>> branchUserData;
    private final MutableLiveData<BranchLoginParams> branchUserObj = new MutableLiveData();


    //Check saved login
    private LiveData<List<Branch>> savedBranchLoginData;
    private final MutableLiveData<Boolean> savedBranchLoginObj = new MutableLiveData();


    //delete saved login
    private LiveData<Resource<Boolean>> deleteBranchLoginData;
    private final MutableLiveData<Boolean> deleteBranchLoginObj = new MutableLiveData();


    //Get branch profile
    private LiveData<Resource<BranchProfileData>> branchProfileData;
    private final MutableLiveData<String> branchProfileObj = new MutableLiveData();


    public Branch branchLogin;

    public BranchViewModel(@NonNull Application application) {
        super(application);

        branchRepository = ((CoreApp)application).getBranchRepository();

        //Login
        branchUserData = Transformations.switchMap(branchUserObj, obj ->{
            if(obj==null){
                return new MutableLiveData<>();
            }
            return branchRepository.branchLogin(obj.email,obj.password);
        });

        //Saved Login
        savedBranchLoginData = Transformations.switchMap(savedBranchLoginObj, obj ->{
            if(obj==null || obj ==false){
                return new MutableLiveData<>();
            }
            return branchRepository.getSavedBranchLogin();
        });


        //delete branch login
        deleteBranchLoginData = Transformations.switchMap(deleteBranchLoginObj, isDelete->{
           if(isDelete ==false){
               return new MutableLiveData<>();
           }
           return branchRepository.deleteBranchLogin();
        });


        //get branch profile data
        branchProfileData = Transformations.switchMap(branchProfileObj, branchId->{
            if(branchId==null || branchId.isEmpty()){
                return new MutableLiveData<>();
            }
            return branchRepository.getBranchProfileData(branchId);
        });

    }

    //--------------login --------------start
    public LiveData<Resource<BranchLoginDTO>> getBranchLoginData(){
        return branchUserData;
    }
    public void setBranchUserObj(String email, String password){
        branchUserObj.postValue(new BranchLoginParams(email,password));
    }
    private class BranchLoginParams {
        private String email;
        private String password;

        public BranchLoginParams(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
    //--------------login --------------end



    //Saved branch login data
    public LiveData<List<Branch>> getSavedBranchLoginData(){
        savedBranchLoginObj.postValue(true);
        return savedBranchLoginData;
    }

    //delete branch login data
    public LiveData<Resource<Boolean>> getDeleteBranchLoginData(){
        return deleteBranchLoginData;
    }
    public void setBranchloginDelete(boolean isDelete){
        this.deleteBranchLoginObj.postValue(isDelete);
    }

    //get branch profile data
    public LiveData<Resource<BranchProfileData>> getBranchProfileData(){
        return branchProfileData;
    }
    public void setBranchProfileObj(String branchId){
        this.branchProfileObj.postValue(branchId);
    }

}
