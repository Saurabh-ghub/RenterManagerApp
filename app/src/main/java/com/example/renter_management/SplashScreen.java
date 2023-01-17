package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation top_anim, bottom_anime;
    ImageView img;
    TextView tle, slg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        top_anim = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.top_anime);
        bottom_anime = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bottom_anime);
        tle = findViewById(R.id.title);
        slg = findViewById(R.id.slogan);
        img = findViewById(R.id.imageView);

        img.setAnimation(top_anim);
        tle.setAnimation(bottom_anime);
        slg.setAnimation(bottom_anime);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);

//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,img,"logo_img");
                startActivity(i);
                finish();
            }
        },3000);
    }
}