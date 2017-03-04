package com.footprynt.footprynt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AnalysisFragment extends Fragment {

    private ViewGroup rootView;

    public static AnalysisFragment newInstance(){
        AnalysisFragment fragment = new AnalysisFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_analysis, container, false);
        return rootView;
    }
}