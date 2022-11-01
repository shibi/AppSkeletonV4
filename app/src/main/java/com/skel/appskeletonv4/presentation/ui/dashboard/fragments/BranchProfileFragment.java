package com.skel.appskeletonv4.presentation.ui.dashboard.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.data.remotedb.dto.branchProfile.BranchProfileData;
import com.skel.appskeletonv4.data.remotedb.dto.login.Branch;
import com.skel.appskeletonv4.domain.common.utils.AppDialogs;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.presentation.ui.common.SharedFragment;
import com.skel.appskeletonv4.presentation.ui.dashboard.DashboardActivity;
import com.skel.appskeletonv4.presentation.viewmodels.branch.BranchViewModel;

import java.util.List;

public class BranchProfileFragment extends SharedFragment{

    private AppCompatButton btn_logout;
    private AppDialogs appDialogs;
    private BranchViewModel branchViewModel;
    private AppDialogs progressDialog;
    private AppCompatTextView tv_branchName,tv_branchPlace, tv_mobile,tv_email;
    private ImageView iv_back;

    public BranchProfileFragment() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_branch_profile;
    }

    @Override
    public void onCreateView(View getView) {

        appDialogs = new AppDialogs(getContext());
        progressDialog = new AppDialogs(getContext());

        tv_branchName = getView.findViewById(R.id.branchName);
        tv_branchPlace = getView.findViewById(R.id.branchPlace);
        tv_mobile = getView.findViewById(R.id.branchPhone);
        tv_email= getView.findViewById(R.id.branchEmail);
        iv_back = getView.findViewById(R.id.iv_pl_back);

        btn_logout = getView.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(view -> {
            try{

                logoutConfirmation();

            }catch(Exception e){
                e.printStackTrace();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if(getActivity()!=null){
                        ((DashboardActivity)getActivity()).gotoHomeFragment(true);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        branchViewModel.setLoadingState(true);
    }

    @Override
    public void initViewModels() {
        branchViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(BranchViewModel.class);
    }

    @Override
    public void initObservers() {

        branchViewModel.getLoadingState().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean){
                progressDialog.showProgressBar();
            }else {
                progressDialog.hideProgressbar();
            }
        });

        branchViewModel.getSavedBranchLoginData().observe(getViewLifecycleOwner(), new Observer<List<Branch>>() {
            @Override
            public void onChanged(List<Branch> branches) {
                try {

                    branchViewModel.setLoadingState(false);

                    if(branches!=null){
                        if(branches.size()>0){
                            branchViewModel.branchLogin = branches.get(0);
                            String branchId = ""+branchViewModel.branchLogin.getId();
                            setBranchProfileObj(branchId);
                        }
                    }else {
                        showToast("Invalid branchId. Please retry");
                    }

                }catch (Exception e){

                }
            }
        });

        branchViewModel.getBranchProfileData().observe(getViewLifecycleOwner(), new Observer<Resource<BranchProfileData>>() {
            @Override
            public void onChanged(Resource<BranchProfileData> branchProfileData) {
                try {

                    branchViewModel.setLoadingState(false);

                    if(branchProfileData!=null){
                        switch (branchProfileData.status)
                        {
                            case LOADING:
                                break;
                            case SUCCESS:
                                populateBranchProfileData(branchProfileData.data);
                                break;
                            case ERROR:
                                break;
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        branchViewModel.getDeleteBranchLoginData().observe(getViewLifecycleOwner(), new Observer<Resource<Boolean>>() {
            @Override
            public void onChanged(Resource<Boolean> booleanResource) {
                try {

                    branchViewModel.setLoadingState(false);

                    if(booleanResource!=null){
                        switch (booleanResource.status)
                        {
                            case LOADING:
                                break;
                            case SUCCESS:
                                if(booleanResource.data){
                                    ((DashboardActivity) getActivity()).clearForLogout();
                                }
                                break;
                            case ERROR:
                                break;
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void populateBranchProfileData(BranchProfileData profileData){
        try {

            tv_branchName.setText(profileData.getName());
            tv_branchPlace.setText(profileData.getAddress());
            tv_mobile.setText(profileData.getMobile());
            tv_email.setText(profileData.getEmail());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logoutConfirmation(){
        try{

            appDialogs.showLogoutConfirmation(new AppDialogs.OnDualActionButtonClickListener() {
                @Override
                public void onClickPositive(String id) {
                    try {

                        setDeleteDataForLogout();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClickNegetive(String id) {

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setDeleteDataForLogout(){
        branchViewModel.setLoadingState(true);
        branchViewModel.setBranchloginDelete(true);
    }

    private void setBranchProfileObj(String branchId){
        branchViewModel.setLoadingState(true);
        branchViewModel.setBranchProfileObj(branchId);
    }

    @Override
    public void onFragmentBackPress(Context context) {
        //do backpress actions here
        ((DashboardActivity)context).gotoHomeFragment(true);
    }
}
