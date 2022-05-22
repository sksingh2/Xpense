package com.example.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class Launch_screen extends AppCompatActivity {
    VideoView vi;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        vi = findViewById(R.id.videoView);
        //set the path of the video that we need to use in our VideoView
        vi.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.splash_screen);
        //start() method of the VideoView class will start the video to play
        vi.start();
        handler = new Handler();
        handler.postDelayed(() -> {
            Intent i =new Intent(Launch_screen.this,LogIn_Page.class);
            startActivity(i);
            finish();
        },3150);

    }
}