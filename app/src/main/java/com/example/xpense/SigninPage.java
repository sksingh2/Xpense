package com.example.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SigninPage extends AppCompatActivity {
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);
         t1 = findViewById(R.id.app_name);
         t1.setText("Hello");

        Toast.makeText(this, "This is the signin page", Toast.LENGTH_SHORT).show();
    }
}