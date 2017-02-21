package com.footprynt.footprynt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private ViewGroup rootView;

    public static HomeFragment newInstance(){
        HomeFragment homefragment = new HomeFragment();
        return homefragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.home_layout, container, false);
        return rootView;
    }
}