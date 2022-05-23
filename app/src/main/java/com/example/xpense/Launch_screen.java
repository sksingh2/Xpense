package com.example.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Launch_screen extends AppCompatActivity {
    VideoView vi;
    Handler handler;
    FirebaseUser user_check ;

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
            user_check = FirebaseAuth.getInstance().getCurrentUser();
            if (user_check!= null)
            {
                startActivity(new Intent(Launch_screen.this,bottom_navigation.class));
                finish();
            }
            else
            {
                Intent intent = new Intent (Launch_screen.this,LogIn_Page.class);
                startActivity(intent);
                finish();
            }
        }, 3150);
    }
}