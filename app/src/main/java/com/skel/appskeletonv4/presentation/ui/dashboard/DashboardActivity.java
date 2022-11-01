package com.skel.appskeletonv4.presentation.ui.dashboard;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.skel.appskeletonv4.R;
import com.skel.appskeletonv4.domain.common.Constants;
import com.skel.appskeletonv4.domain.common.utils.SharedPrefHelper;
import com.skel.appskeletonv4.presentation.ui.common.ISharedFragment;
import com.skel.appskeletonv4.presentation.ui.common.SharedActivity;
import com.skel.appskeletonv4.presentation.ui.dashboard.fragments.BranchProfileFragment;
import com.skel.appskeletonv4.presentation.ui.dashboard.fragments.HomeFragment;
import com.skel.appskeletonv4.presentation.ui.dashboard.fragments.PackageListFragment;
import com.skel.appskeletonv4.presentation.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends SharedActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    public int setUpLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void initViews() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {

                    if (item.isChecked()) {
                        return false;
                    }

                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            gotoHomeFragment(false);
                            return true;
                        case R.id.menu_recent:
                            loadFragment(new PackageListFragment(), Constants.NAV_TAG_RECENTS);
                            return true;
                        case R.id.menu_profile:
                            loadFragment(new BranchProfileFragment(), Constants.NAV_TAG_PROFILE);
                            return true;
                        default:
                            return false;
                    }

                } catch (Exception e) {
                    return false;
                }
            }
        });

        loadFragment(new HomeFragment(),Constants.NAV_TAG_HOME);

    }

    @Override
    public void initViewModels() {

    }

    @Override
    public void initObservers() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void gotoHomeFragment(boolean isShowClick){
        if(isShowClick){
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }

        loadFragment(new HomeFragment(), Constants.NAV_TAG_HOME);
    }

    /**
     *  clear for logout
     * */
    public void clearForLogout(){
        try{

            SharedPrefHelper.getInstance(this).saveLogin("","");
            gotoLoginScreen();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gotoLoginScreen(){

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    public void loadFragment(Fragment fragment, String tag) {
        try {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, tag)
                    .commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        ((ISharedFragment)currentFragment).onFragmentBackPress(this);

    }
}