package com.skel.appskeletonv4.presentation.ui.apploading;

import android.content.Intent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.domain.common.utils.SharedPrefHelper;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.presentation.ui.common.SharedActivity;
import com.skel.appskeletonv4.presentation.ui.dashboard.DashboardActivity;
import com.skel.appskeletonv4.presentation.ui.login.LoginActivity;
import com.skel.appskeletonv4.presentation.viewmodels.branch.BranchViewModel;

import java.util.List;

public class SplashActivity extends SharedActivity {


    private BranchViewModel branchViewModel;

    @Override
    public int setUpLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViewModels() {
        branchViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BranchViewModel.class);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initObservers() {


        branchViewModel.getSavedBranchLoginData().observe(this, new Observer<List<Branch>>() {
            @Override
            public void onChanged(List<Branch> branches) {
                try{

                    if(branches!=null && branches.size() > 0) {

                       String credentials = SharedPrefHelper.getInstance(SplashActivity.this).getSavedLogin();
                       String[] creds = credentials.split(":");
                       if(creds!=null && creds.length>1){
                           String username = creds[0];
                           String password = creds[1];
                           setUserLogin(username, password);

                       }else {
                           gotoLoginScreen();
                       }

                    }else {
                        gotoLoginScreen();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        branchViewModel.getBranchLoginData().observe(this, new Observer<Resource<BranchLoginDTO>>() {
            @Override
            public void onChanged(Resource<BranchLoginDTO> branchLoginDTOResource) {
                try {

                    if(branchLoginDTOResource!=null){
                        switch (branchLoginDTOResource.status)
                        {
                            case LOADING:
                                break;
                            case SUCCESS:

                                gotoDashboard();

                                break;
                            case ERROR:

                                gotoLoginScreen();
                                showToast(getString(R.string.request_relogin));

                                break;
                        }
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void setUserLogin(String username, String password){

        //remove saved branch login
        branchViewModel.getSavedBranchLoginData().removeObservers(this);

        branchViewModel.setLoadingState(true);
        branchViewModel.setBranchUserObj(username, password);
    }

    private void gotoLoginScreen(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void gotoDashboard(){
        Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        startActivity(dashboardIntent);
        finish();
    }
}