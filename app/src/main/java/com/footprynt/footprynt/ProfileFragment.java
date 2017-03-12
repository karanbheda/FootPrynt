package com.footprynt.footprynt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private ViewGroup rootView;
    private CircleImageView twitter, fb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        fb = (CircleImageView) rootView.findViewById(R.id.check_fb);
        twitter = (CircleImageView) rootView.findViewById(R.id.check_twitter);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.setImageDrawable(getResources().getDrawable(R.drawable.ic_active_fb));
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitter.setImageDrawable(getResources().getDrawable(R.drawable.ic_active_twitter));
            }
        });
        return rootView;
    }

}
