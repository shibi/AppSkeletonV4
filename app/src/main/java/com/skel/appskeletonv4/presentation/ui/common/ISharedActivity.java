package com.skel.appskeletonv4.presentation.ui.common;

import android.os.Bundle;

public interface ISharedActivity {

    int setUpLayout();
    void initViewModels();
    void initViews(Bundle savedState);
    void initObservers();

}
