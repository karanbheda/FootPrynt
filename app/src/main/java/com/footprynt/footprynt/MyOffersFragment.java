package com.footprynt.footprynt;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import java.util.ArrayList;
import java.util.List;

public class MyOffersFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<Category> categoryList;
    private ViewGroup rootView;
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_offers, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(), categoryList);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        preparecategories();

        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        return rootView;
    }

    private void displayLocation() {
        if ( ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
            ActivityCompat.requestPermissions(getActivity(),
                     new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1234);

        if ( ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED ) {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            Toast.makeText(getContext(),latitude+","+longitude, Toast.LENGTH_SHORT).show();

        } }else {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            //Toast.makeText(getContext(),"NO PERMISSION", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                getActivity().finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        displayLocation();
    }

    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    private void preparecategories() {

        Category a = new Category("Journalism",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Movies",R.drawable.ic_movies);
        categoryList.add(a);

        a = new Category("Restaurants",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Breweries",R.drawable.ic_pub);
        categoryList.add(a);

        a = new Category("Electronics",R.drawable.ic_ecommerce);
        categoryList.add(a);

        a = new Category("Real Estate",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Books",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Sports",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("Humour",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Hotels",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Automobiles",R.drawable.ic_automobile);
        categoryList.add(a);

        a = new Category("Health",R.drawable.ic_health);
        categoryList.add(a);

        a = new Category("Travel",R.drawable.ic_travel);
        categoryList.add(a);

        a = new Category("FMCG",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Banks",R.drawable.ic_bank);
        categoryList.add(a);

        a = new Category("Personal Care",R.drawable.ic_health);
        categoryList.add(a);

        a = new Category("Shares",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Consumer Durables",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("E-Commerce",R.drawable.ic_ecommerce);
        categoryList.add(a);

        a = new Category("Education",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Retail",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("Fashion",R.drawable.ic_travel);
        categoryList.add(a);

        a = new Category("TV",R.drawable.ic_tv);
        categoryList.add(a);

        adapter.notifyDataSetChanged();
    }

}

