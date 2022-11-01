package com.skel.appskeletonv4.presentation.ui.common;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class SharedActivity extends AppCompatActivity implements ISharedActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            int layout = setUpLayout();
            setContentView(layout);

            initViewModels();

            initObservers();

            initViews();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }

}
