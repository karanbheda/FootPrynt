package com.footprynt.footprynt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private ViewGroup rootView;
    private CircleImageView twitter, fb,edit_dp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        fb = (CircleImageView) rootView.findViewById(R.id.check_fb);
        twitter = (CircleImageView) rootView.findViewById(R.id.check_twitter);
        edit_dp = (CircleImageView) rootView.findViewById(R.id.btn_change_dp);

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

        edit_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.change_display_box, rootView, false);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        return rootView;
    }

}
