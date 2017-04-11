package com.footprynt.footprynt;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    android.support.v7.widget.Toolbar toolbar;
    private boolean home=true;
    private static final long DRAWER_DELAY = 250;
    private MenuItem noti;
    String backStageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x=1;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nv) ;
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            HomeFragment fragment = HomeFragment.newInstance();
            mFragmentTransaction.replace(R.id.containerView, fragment).commit();
        }

        NavigationMenuView navigationMenuView = (NavigationMenuView) mNavigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        mNavigationView.getMenu().getItem(0).setChecked(true);
        /*mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                mFragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                if (menuItem.getItemId() == R.id.nav_home) {
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView,new HomeFragment()).commit();
                    home=true;
                }

                else if (menuItem.getItemId() == R.id.nav_myoffers) {
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView,new MyOffersFragment()).commit();
                    toolbar.setTitle("My Offers");
                    home=false;
                    x=1;
                }
                else if (menuItem.getItemId() == R.id.nav_profile) {
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView,new ProfileFragment()).commit();
                    toolbar.setTitle("Profile");
                    home=false;
                    x=1;
                }
                else if (menuItem.getItemId() == R.id.nav_analysis) {
                    Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                    home=false;
                    x=1;
                }
                else if (menuItem.getItemId() == R.id.nav_pp)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.footprynt.in/privacyPolicy"));
                    startActivityForResult(intent,0);
                }
                else if (menuItem.getItemId() == R.id.nav_share)
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey there, footprynt is a social media market place. If you are active on social media and if you are looking for great offers on various brands, download the app now https://play.google.com/store/apps/details?id=com.ionicframework.footprynt448731");
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "FootPrynt"));
                }
                return false;
            }

        });*/
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        mDrawerLayout.closeDrawers();
                        if(!item.isChecked()) {
                            final FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                            mFragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                            switch (item.getItemId()) {
                                case R.id.nav_home:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            boolean isFragmentInStack = mFragmentManager.popBackStackImmediate(backStageName, 0);
                                            if (!isFragmentInStack) {
                                                HomeFragment fragment = HomeFragment.newInstance();
                                                mFragmentTransaction.replace(R.id.containerView, fragment);
                                                backStageName = fragment.getClass().getName();
                                                mFragmentTransaction.addToBackStack(backStageName).commit();
                                            }
                                            toolbar.setTitle("Home");
                                            home=true;
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.nav_profile:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            mFragmentTransaction.replace(R.id.containerView, new ProfileFragment());
                                            mFragmentTransaction.addToBackStack(null).commit();
                                            toolbar.setTitle("Profile");
                                            home=false;
                                            x=1;
                                            
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.nav_my_offers:
                                    if ( ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
                                        ActivityCompat.requestPermissions(getParent(),
                                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1234);
                                    final LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE );
                                    if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                                        buildAlertMessageNoGps();
                                    }
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            mFragmentTransaction.replace(R.id.containerView, new MyOffersFragment());
                                            mFragmentTransaction.addToBackStack(null).commit();
                                            toolbar.setTitle("My Offers");
                                            home=false;
                                            x=1;

                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.nav_analysis:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            mFragmentTransaction.replace(R.id.containerView, new AnalysisFragment());
                                            mFragmentTransaction.addToBackStack(null);
                                            mFragmentTransaction.commit();
                                            toolbar.setTitle("Analysis");
                                            //Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                                            home=false;
                                            x=1;

                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.nav_pp:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent=new Intent(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse("https://www.footprynt.in/privacyPolicy"));
                                            startActivityForResult(intent,0);
                                        }
                                    }, DRAWER_DELAY);
                                    break;

                                case R.id.nav_share:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent sendIntent = new Intent();
                                            sendIntent.setAction(Intent.ACTION_SEND);
                                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey there, footprynt is a social media market place. If you are active on social media and if you are looking for great offers on various brands, download the app now https://play.google.com/store/apps/details?id=com.ionicframework.footprynt448731");
                                            sendIntent.setType("text/plain");
                                            startActivity(Intent.createChooser(sendIntent, "FootPrynt"));
                                        }
                                    }, DRAWER_DELAY);
                                    return true;

                            }
                        }
                        return true;
                    }
                }
        );


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();




    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                startActivity(new Intent(this, NotificationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    int x=1;


    /*@Override
    public void onBackPressed() {

            if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.mDrawerLayout.closeDrawer(GravityCompat.START);
            }

        if(home&&x==0&&!this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed();
            finish();
        }
        else if(home&&x!=0&&!this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
         x=0;
            Toast.makeText(this,"Press again to exit", Toast.LENGTH_SHORT).show();
            Thread t = new Thread(){
                @Override
                public void run() {
                    try{
                        Thread.sleep(3000);
                        x=1;
                    }
                    catch(Exception E){};

                }
            };
            t.start();
        }
        else    {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView,new HomeFragment()).commit();
            toolbar.setTitle("Home");
            home=true;
        }
    }*/
    @Override
    public void onBackPressed()
    {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawers();
        else {
            mNavigationView.getMenu().getItem(0).setChecked(true);
            toolbar.setTitle("Home");
            super.onBackPressed();
        }
    }

}