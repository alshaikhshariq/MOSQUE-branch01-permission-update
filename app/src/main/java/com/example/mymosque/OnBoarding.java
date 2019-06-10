package com.example.mymosque;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mymosque.Adapter.MyPagerAdapter;

import java.util.Objects;

public class OnBoarding extends AppCompatActivity
{

    private int[]layouts;
    private Button btnSkip;
    private Button btnNext;
    private MyPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //window
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE)
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        //Hide Actionbar and For full screen

        viewPager = findViewById(R.id.viewpager_Onboarding);
        btnNext = findViewById(R.id.NextBtn);
        btnSkip = findViewById(R.id.SKIPbtn);

        layouts = new int[]{R.layout.slider_1,R.layout.slider_2, R.layout.slider_3,R.layout.slider_4,R.layout.slider_5};
        pagerAdapter = new MyPagerAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);


    }//onCreate Method

    @Override
    protected void onStart()
    {
        super.onStart();

        //When user press skip, start Main Activity
        btnSkip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(OnBoarding.this, PermissionActivity.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length)
                {
                    //move to next page
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startActivity(new Intent(OnBoarding.this, PermissionActivity.class));
                    finish();
                }
            }
        });

        if(!isFirstTimeStartApp())
        {
            SharedPreferences sharedPreferences = this.getSharedPreferences("USER_PREFERENCE", 0);
            int userId = sharedPreferences.getInt("ID", 0);
            if(userId == 0)
            {
                startActivity(new Intent(OnBoarding.this, PermissionActivity.class));
                finish();
            }
            else {
                startMainActivity();
                finish();
            }
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int position)
            {
                if(position == layouts.length-1)
                {
                    //LAST PAGE
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.GONE);
                }else {
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private boolean isFirstTimeStartApp()
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag", true);
    }

    @SuppressLint("ApplySharedPref")
    private void setFirstTimeStartStatus()
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag", false);
        editor.commit();
    }

    /* private void setDotStatus(int page){
         layoutDot.removeAllViews();
         dotstv =new TextView[layouts.length];
         for (int i = 0; i < dotstv.length; i++) {
             dotstv[i] = new TextView(this);
             dotstv[i].setText(Html.fromHtml("&#8226;"));
             dotstv[i].setTextSize(60);
             dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
             layoutDot.addView(dotstv[i]);
         }
         //Set current dot active
         if(dotstv.length>0){
             dotstv[page].setTextColor(Color.parseColor("#ffffff"));
         }
     }*/
    private void startMainActivity()
    {
        setFirstTimeStartStatus();
        startActivity(new Intent(OnBoarding.this, MainActivity.class));
        finish();
    }
    private void setStatusBarTransparent()
    {
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
