package com.example.mymosque;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymosque.Fragments.FragmentAddMosque;
import com.example.mymosque.Fragments.FragmentDua;
import com.example.mymosque.Fragments.FragmentFavorite;
import com.example.mymosque.Fragments.FragmentFeedback;
import com.example.mymosque.Fragments.FragmentHome;
import com.example.mymosque.Fragments.FragmentMasajidList;
import com.example.mymosque.Fragments.FragmentMyMosque;
import com.example.mymosque.Fragments.FragmentNearestJummah;
import com.example.mymosque.Fragments.FragmentNearestMasajid;
import com.example.mymosque.Fragments.FragmentNotification;
import com.example.mymosque.Fragments.FragmentQiblaDirection;
import com.example.mymosque.Fragments.FragmentSettings;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    ImageView Humbburger;
    DrawerLayout mDrawerLayout;
    ImageView backbutton;
    TextView shareapp;
    private DrawerLayout drawerLayout;

    //Creating SharedPreferences Object
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;
    SharedPreferences sharedPreferences3;

    private Handler h = new Handler();
    private Runnable runnable;
    private int delay = (5000);

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences     = this.getSharedPreferences("GetPrimaryMosque", MODE_PRIVATE);
        sharedPreferences2    = this.getSharedPreferences("LatLong", MODE_PRIVATE);
        sharedPreferences3    = this.getSharedPreferences("USER_PREFERENCE", MODE_PRIVATE);

        shareapp = findViewById(R.id.text12);

        //Button Calls Navigation view
        backbutton      = findViewById(R.id.backButton);
        Humbburger      = findViewById(R.id.humburgerIcon);
        mDrawerLayout   = findViewById(R.id.drawer_layout);


        TextView home_                      =  findViewById(R.id.text1);
        TextView Feedback_                  =  findViewById(R.id.text11);
        TextView Dua_                       =  findViewById(R.id.text9);
        TextView fragmentmassajilist_       =  findViewById(R.id.text3);
        TextView fragmentnearestmassajid_   =  findViewById(R.id.text4);
        TextView fragmentnearestjummah_     =  findViewById(R.id.text5);
        TextView fragmentsettings_          =  findViewById(R.id.text10);
        TextView fragmentnotification_      =  findViewById(R.id.text7);
        TextView fragmentaddmosque_         =  findViewById(R.id.text8);
        TextView fragqibla                  =  findViewById(R.id.text6);
        TextView fragmentfavorite           =  findViewById(R.id.text2);

        home_                   .setOnClickListener(this);
        Feedback_               .setOnClickListener(this);
        Dua_                    .setOnClickListener(this);
        fragmentmassajilist_    .setOnClickListener(this);
        fragmentnearestmassajid_.setOnClickListener(this);
        fragmentnearestjummah_  .setOnClickListener(this);
        fragmentsettings_       .setOnClickListener(this);
        fragmentnotification_   .setOnClickListener(this);
        fragmentaddmosque_      .setOnClickListener(this);
        fragqibla               .setOnClickListener(this);
        fragmentfavorite        .setOnClickListener(this);

        getLocation();

        FragmentHome myf = new FragmentHome();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.Screen_Area, myf);
        transaction.commit();

        drawerLayout = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        shareapp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        Humbburger.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v)
            {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showHome();

            }
        });

        final int primaryId         = sharedPreferences.getInt("PM_ID", 0);
        final String primaryName    = sharedPreferences.getString("PM_NAME", "");
        final String latitude       = sharedPreferences2.getString("Lat", "");
        final String longitude      = sharedPreferences2.getString("Long", "");
        final int UserId            = sharedPreferences3.getInt("ID", 0);

        Toast.makeText(getApplicationContext(), "Primary Mosque ID:" + primaryId, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Primary Mosque Name:" + primaryName, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Latitude:" + latitude, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Longitude:" + longitude, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "User ID: " + UserId, Toast.LENGTH_LONG).show();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,  R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View v)
            {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v)
            {
                super.onDrawerOpened(v);
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item)
            {
                if (item != null && item.getItemId() == android.R.id.home)
                {
                    if (drawerLayout.isDrawerOpen(Gravity.RIGHT))
                    {
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    } else
                        {
                        drawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
                return false;
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }


    private boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }/* else{

            showHome();
        }*/

        if (doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;

        }
        else{

            showHome();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);



    }



 /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    Fragment fragment = null;
    private  void showHome(){

        fragment = new FragmentHome();
        if(fragment!=null){

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.Screen_Area, fragment ,fragment.getTag()).commit();
        }
    }

    @Override
    public void onClick(View v)
    {
        Fragment fragment = null;
        Class fragmentClass = null;

        if(v.getId() == R.id.text1)
        {
            //do something here if button1 is clicked

            fragmentClass = FragmentMyMosque.class;
        }
        else if (v.getId() == R.id.text4)
        {
            fragmentClass = FragmentNearestMasajid.class;
            //do something here if button3 is clicked
           // fragmentClass = FragmentLevel.class;
        }else if (v.getId() == R.id.text5){
            //do something here if button3 is clicked
           // fragmentClass = FragmentNotification.class;
            fragmentClass = FragmentNearestJummah.class;
        }
        else if (v.getId() == R.id.text6)
        {
            //do something here if button3 is clicked
           // fragmentClass = FragmentAboutUS.class;
            fragmentClass = FragmentQiblaDirection.class;
        }
        else if (v.getId() == R.id.text11)
        {
            //do something here if button3 is clicked
           fragmentClass = FragmentFeedback.class;
        }
        else if (v.getId() == R.id.text9)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentDua.class;
        }
        else if (v.getId() == R.id.text3)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentMasajidList.class;
        }
        else if (v.getId() == R.id.text10)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentSettings.class;
        }

        else if (v.getId() == R.id.text7)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentNotification.class;
        }

        else if (v.getId() == R.id.text8)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentAddMosque.class;
        }
        else if (v.getId() == R.id.text2)
        {
            //do something here if button3 is clicked
            fragmentClass = FragmentFavorite.class;
        }


        if(fragment!=null)
        {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.Screen_Area, fragment ,fragment.getTag()).commit();
        }



        try
        {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Screen_Area, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void getLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,ACCESS_COARSE_LOCATION)  == PackageManager.PERMISSION_GRANTED)
        {

            FusedLocationProviderClient fusedLocation = LocationServices.getFusedLocationProviderClient(this);
            fusedLocation.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>()
            {
                @Override
                public void onSuccess(Location location)
                {
                    if (location != null)
                    {
                        double mLastLongitude   = location.getLongitude();
                        double mLastLatitude    = location.getLatitude();
                        Log.d("Longitude", ""+ mLastLongitude);
                        Log.d("Latitude", ""+ mLastLatitude);
                        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("LatLong", MODE_PRIVATE).edit();
                        editor.putString("Lat", String.valueOf(mLastLongitude));
                        editor.putString("Long", String.valueOf(mLastLatitude));
                        editor.apply();
                    }
                    else
                        Toast.makeText(getApplicationContext(), " Location is showing null ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        h.postDelayed( runnable = new Runnable()
        {
            public void run()
            {
                getLocation();
                Log.d("My Msg", "update location called");
                h.postDelayed(runnable, delay);
                Log.d("Location Status: ", "Location Updated");
                Log.d("Location Status: ", "Location Updated");

            }
        }, delay);
    }
}
