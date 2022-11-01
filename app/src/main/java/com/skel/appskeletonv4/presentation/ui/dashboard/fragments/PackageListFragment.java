package com.skel.appskeletonv4.presentation.ui.dashboard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.data.remotedb.dto.package_list.PackageDTO;
import com.skel.appskeletonv4.domain.common.Constants;
import com.skel.appskeletonv4.domain.common.utils.AppDialogs;
import com.skel.appskeletonv4.domain.common.utils.api_util.Resource;
import com.skel.appskeletonv4.presentation.ui.common.SharedFragment;
import com.skel.appskeletonv4.presentation.ui.dashboard.DashboardActivity;
import com.skel.appskeletonv4.presentation.ui.dashboard.adapter.AllPackageAdapter;
import com.skel.appskeletonv4.presentation.viewmodels.plans.PackagesViewModel;

import java.util.List;

public class PackageListFragment extends SharedFragment {

    private RecyclerView rv_packages;
    private AllPackageAdapter packageAdapter;
    private PackagesViewModel packagesViewModel;
    private AppDialogs appDialogs;
    private String customerId;

    public PackageListFragment(){

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_package_list;
    }

    @Override
    public void onCreateView(View getView) {

        appDialogs = new AppDialogs(getContext());

        customerId = "";
        Bundle bundle = getArguments();
        if(bundle!=null){
            customerId = bundle.getString(Constants.CUSTOMER_ID);
            if(customerId == null || customerId.isEmpty()){
                customerId = "";
            }
        }


        packageAdapter = new AllPackageAdapter(Constants.PACKAGE_NORMAL_LIST, new AllPackageAdapter.OnPackageSelectionListener() {
            @Override
            public void onSelectPackage(PackageDTO selectedPackage) {
                try {

                    String packageId = ""+selectedPackage.getPackageId();
                    String amount  = ""+selectedPackage.getPackageRate();

                    gotoPackageServiceListFragment(customerId, packageId, amount);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        rv_packages = getView.findViewById(R.id.rv_list_package);
        rv_packages.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_packages.setAdapter(packageAdapter);


        getAllPackages();

    }

    @Override
    public void initViewModels() {
        packagesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PackagesViewModel.class);
    }

    @Override
    public void initObservers() {

        packagesViewModel.getLoadingState().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {

                if(isLoading){
                    appDialogs.showProgressBar();
                }else {
                    appDialogs.hideProgressbar();
                }

            }
        });

        packagesViewModel.getAllPackageData().observe(getViewLifecycleOwner(), new Observer<Resource<List<PackageDTO>>>() {
            @Override
            public void onChanged(Resource<List<PackageDTO>> listResource) {
                try{

                    //hide progressbar
                    packagesViewModel.setLoadingState(false);

                    if(listResource!=null){

                        switch (listResource.status)
                        {
                            case LOADING:
                                Log.e("--------------","load");
                                break;
                            case SUCCESS:
                                Log.e("--------------","succes");
                                if(listResource.data!=null && listResource.data.size()>0){

                                    packageAdapter.replace(null);
                                    packageAdapter.replace(listResource.data);

                                }else {
                                    //show package empty
                                }

                                break;
                            case ERROR:
                                Log.e("--------------","error");
                                break;
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void getAllPackages(){
        packagesViewModel.setLoadingState(true);
        packagesViewModel.setAllPackageObj(true);
    }


    private void gotoPackageServiceListFragment(String custID, String packageId, String packAmount){
        //nothing
    }

    @Override
    public void onFragmentBackPress(Context context) {
        //do backpress actions here
        ((DashboardActivity)context).gotoHomeFragment(true);
    }
}
