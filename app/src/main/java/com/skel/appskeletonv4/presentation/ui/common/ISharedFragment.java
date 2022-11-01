package com.skel.appskeletonv4.presentation.ui.common;

import android.content.Context;
import android.view.View;

public interface ISharedFragment {

    int setContentLayout();
    void onCreateView(View getView);
    void initViewModels();
    void initObservers();
    void onFragmentBackPress(Context context);
}
