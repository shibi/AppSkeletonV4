package com.skel.appskeletonv4.presentation.ui.dashboard.fragments;

import android.content.Context;
import android.view.View;
import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.domain.common.utils.AppDialogs;
import com.skel.appskeletonv4.presentation.ui.common.SharedFragment;

public class HomeFragment extends SharedFragment {

    public HomeFragment() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateView(View getView) {

    }

    @Override
    public void initViewModels() {

    }

    @Override
    public void initObservers() {

    }

    @Override
    public void onFragmentBackPress(Context context) {
        //do back press actions here

        //show exit alert dialog
        AppDialogs appDialogs = new AppDialogs(getContext());
        appDialogs.showLogoutConfirmation(new AppDialogs.OnDualActionButtonClickListener() {
            @Override
            public void onClickPositive(String id) {
                // finish
                getActivity().finish();
            }

            @Override
            public void onClickNegetive(String id) {

            }
        });
    }
}
