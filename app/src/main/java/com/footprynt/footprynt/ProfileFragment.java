package com.footprynt.footprynt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private ViewGroup rootView;
    private CircleImageView twitter, fb,edit_dp;
    private TextView status_fb, status_twitter;
    private PrefManager prefManager;
    private Button logout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        fb = (CircleImageView) rootView.findViewById(R.id.check_fb);
        twitter = (CircleImageView) rootView.findViewById(R.id.check_twitter);
        edit_dp = (CircleImageView) rootView.findViewById(R.id.btn_change_dp);
        status_fb = (TextView) rootView.findViewById(R.id.status_fb);
        status_twitter = (TextView) rootView.findViewById(R.id.status_twitter);
        logout = (Button) rootView.findViewById(R.id.btn_logout);
        prefManager = new PrefManager(getContext());

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.setImageDrawable(getResources().getDrawable(R.drawable.ic_active_fb));
                status_fb.setText("Connected");
                status_fb.setTextColor(getResources().getColor(R.color.green));
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitter.setImageDrawable(getResources().getDrawable(R.drawable.ic_active_twitter));
                status_twitter.setText("Connected");
                status_twitter.setTextColor(getResources().getColor(R.color.green));
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setFirstTimeLaunch(true);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return rootView;
    }

}
