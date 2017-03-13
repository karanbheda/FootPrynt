package com.footprynt.footprynt;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.uguratar.countingtextview.countingTextView;

public class HomeFragment extends Fragment {
    private countingTextView miles,offer_claimed,exclusive,general;
    private ViewGroup rootView;
    private RelativeLayout rr_general, rr_exclusive;

    public static HomeFragment newInstance(){
        HomeFragment homefragment = new HomeFragment();
        return homefragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.home_layout, container, false);
        //initializing all elements
        miles = (countingTextView) rootView.findViewById(R.id.tv_miles);
        offer_claimed = (countingTextView) rootView.findViewById(R.id.tv_offers_claimed);
        exclusive = (countingTextView) rootView.findViewById(R.id.tv_exclusive);
        general = (countingTextView) rootView.findViewById(R.id.tv_general);
        rr_general = (RelativeLayout) rootView.findViewById(R.id.rr_general);
        rr_exclusive = (RelativeLayout) rootView.findViewById(R.id.rr_exclusive);


        //adding animations to all the numerical values
        miles.animateText(0,10000000);
        offer_claimed.animateText(0,7);
        exclusive.animateText(0,4);
        general.animateText(0,12);

        GradientDrawable drawable1 = (GradientDrawable) rr_exclusive.getBackground();
        drawable1.setStroke(3, getResources().getColor(R.color.orange));

        GradientDrawable drawable2 = (GradientDrawable) rr_general.getBackground();
        drawable2.setStroke(3, getResources().getColor(R.color.purple));


        return rootView;
    }


}