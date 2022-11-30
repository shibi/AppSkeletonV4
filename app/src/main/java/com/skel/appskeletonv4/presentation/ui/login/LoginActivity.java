package com.skel.appskeletonv4.presentation.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.data.remotedb.dto.login.BranchLoginDTO;
import com.skel.appskeletonv4.domain.common.utils.AppDialogs;
import com.skel.appskeletonv4.domain.common.utils.SharedPrefHelper;
import com.skel.appskeletonv4.domain.common.utils.Utility;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.presentation.ui.common.SharedActivity;
import com.skel.appskeletonv4.presentation.ui.dashboard.DashboardActivity;
import com.skel.appskeletonv4.presentation.viewmodels.branch.BranchViewModel;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends SharedActivity {

    private BranchViewModel branchViewModel;
    private AppCompatEditText et_username,et_password;
    private AppCompatButton btn_login;
    private AppDialogs appDialogs;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public int setUpLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedState) {

        appDialogs = new AppDialogs(this);



        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        coordinatorLayout = findViewById(R.id.cl_root);

        Utility.setHideShowPassword(et_password);


        btn_login.setOnClickListener(view -> {
            onClickLogin();
        });

    }

    @Override
    public void initViewModels() {

        branchViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BranchViewModel.class);
    }

    @Override
    public void initObservers() {

        branchViewModel.getBranchLoginData().observe(this, new Observer<Resource<BranchLoginDTO>>() {
            @Override
            public void onChanged(Resource<BranchLoginDTO> branchDTOResource) {
                try {


                    if(branchDTOResource!=null) {

                        appDialogs.hideProgressbar();

                        switch (branchDTOResource.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                processLogin(branchDTOResource.data);
                                break;
                            case ERROR:
                                showSnack(branchDTOResource.message);
                                break;
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void processLogin(BranchLoginDTO loginResponse){
        try {

            if(loginResponse!=null){
                if(loginResponse.getStatus()){
                    SharedPrefHelper.getInstance(this).saveLogin(et_username.getText().toString(),et_password.getText().toString());
                    gotoDashboard();
                }else {
                    showToast(loginResponse.getMessage());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gotoDashboard(){
        Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        startActivity(dashboardIntent);
        finish();
    }

    private void onClickLogin(){
        try {

            if(validateFields()){

                appDialogs.showProgressBar();

                branchViewModel.setLoadingState(true);
                branchViewModel.setBranchUserObj(et_username.getText().toString(), et_password.getText().toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateFields(){

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if(username.isEmpty()){
            showError(et_username,getString(R.string.enter_valid_username));
            return false;
        }

        if(password.isEmpty()){
            showError(et_password,getString(R.string.enter_valid_password));
            return false;
        }

        return true;
    }

    private void showError(EditText field , String msg){
        field.setError(msg);
        field.requestFocus();
    }

    private void showSnack(String msg){
        Snackbar.make(this, coordinatorLayout,msg, Snackbar.LENGTH_SHORT).show();
    }
}