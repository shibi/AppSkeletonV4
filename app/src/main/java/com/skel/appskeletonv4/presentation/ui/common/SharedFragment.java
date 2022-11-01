package com.skel.appskeletonv4.presentation.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class SharedFragment extends Fragment implements ISharedFragment{

    protected View getView;

    public SharedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{

            int content_layout = setContentLayout();
            getView = inflater.inflate(content_layout, container, false);
            initViewModels();
            onCreateView(getView);
            initObservers();
            return getView;

        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showToast(String msg){
        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
