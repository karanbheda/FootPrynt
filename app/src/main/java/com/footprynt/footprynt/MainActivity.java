package com.footprynt.footprynt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private MenuItem noti;

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
        toolbar.setTitle("FootPrynt");
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new HomeFragment());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_home) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new HomeFragment()).commit();
                    home=true;
                }

                else if (menuItem.getItemId() == R.id.nav_myoffers) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new MyOffersFragment()).commit();
                    toolbar.setTitle("My Offers");
                    home=false;
                    x=1;
                }
                else if (menuItem.getItemId() == R.id.nav_profile) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new MyOffersFragment()).commit();
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
                    intent.setData(Uri.parse("http://rentnget.co.in/"));
                    startActivityForResult(intent,0);
                }
                else if (menuItem.getItemId() == R.id.nav_share)
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi. Sending this from FootPrynt");
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "FootPrynt"));
                }
                return false;
            }

        });


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();




    }

    int x=1;


    @Override
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
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new HomeFragment()).commit();
            toolbar.setTitle("Home");
            home=true;
        }
    }

}