package com.footprynt.footprynt;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.uguratar.countingtextview.countingTextView;

public class HomeFragment extends Fragment {
    //private TextView miles;
    private countingTextView miles,offer_claimed;
    private ViewGroup rootView;

    public static HomeFragment newInstance(){
        HomeFragment homefragment = new HomeFragment();
        return homefragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.home_layout, container, false);
        miles = (countingTextView) rootView.findViewById(R.id.tv_miles);
        offer_claimed = (countingTextView) rootView.findViewById(R.id.tv_offers_claimed);
        miles.animateText(0,10000000);
        offer_claimed.animateText(0,7);
        return rootView;
    }


}