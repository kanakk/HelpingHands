package com.example.helpinghands;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=4000;//2 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ImageView imageView=(ImageView) findViewById(R.id.splashLogo);
        TextView textView=(TextView)findViewById(R.id.textView3);
        TextView textView1=findViewById(R.id.textView4);
        //ImageView imageView1=(ImageView) findViewById(R.id.imageView3);
        //ImageView imageView2=(ImageView)findViewById(R.id.imageView2);

        //imageView2.bringToFront();
        textView1.bringToFront();
        textView.bringToFront();
        imageView.bringToFront();
        //imageView1.invalidate();



        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        imageView.startAnimation(animation);

        // imageView=(ImageView) findViewById(R.id.splashLogo);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {


                Intent homeIntent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);


    }
}