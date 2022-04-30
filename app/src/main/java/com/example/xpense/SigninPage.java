package com.example.xpense;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninPage extends AppCompatActivity {
    TextView AppName;
    EditText username,phoneNumber,otpPassword;
    Button verifyOtp,Signin,sendOtp;
    String un,temp,validSecond,validFirst;
    int temp_otp = 1234, otp;
    long phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);
         AppName = findViewById(R.id.signIn);
         username = findViewById(R.id.EnterUsername);
         phoneNumber = findViewById(R.id.EnterPhoneNumber);
         otpPassword = findViewById(R.id.OtpPassword);
         verifyOtp = findViewById(R.id.VerifyOTP);
         Signin = findViewById(R.id.buttonSignUp);
         sendOtp = findViewById(R.id.sendOTP);

         sendOtp.setOnClickListener(v -> {
             un = username.getText().toString();
             temp = phoneNumber.getText().toString();

             if (un.length() >= 20 && temp.length() != 10) {
                 Toast.makeText(SigninPage.this, "Username can't be more than 20 letters and Phone Number is invalid", Toast.LENGTH_SHORT).show();
             } else if(temp.length() != 10){
                 Toast.makeText(SigninPage.this, "Phone Number is invalid", Toast.LENGTH_SHORT).show();
             }else if(un.length() >= 20){
                 Toast.makeText(SigninPage.this, "Username can't be more than 20 letters", Toast.LENGTH_SHORT).show();
             }else{
                 validFirst = "Username and Phone Number is valid";
                 Toast.makeText(SigninPage.this, validFirst, Toast.LENGTH_SHORT).show();
             }
         });

         //otp setup from phone number should be done here
         verifyOtp.setOnClickListener(v -> {
             phoneNo = Long.parseLong(temp);
             if (validFirst == "Username and Phone Number is valid")
             {
             //temp_otp="1234";
             otp = Integer.parseInt(otpPassword.getText().toString());
             if (otp == temp_otp)
             {
                 validSecond = "OTP Verified";
                 Toast valid_otp = Toast.makeText(this, validSecond, Toast.LENGTH_SHORT);
                 valid_otp.show();

             }
             else
             {
                 Toast invalid_otp = Toast.makeText(getApplication(), "Invalid Otp", Toast.LENGTH_SHORT);
                 invalid_otp.show();
             }
             }
         });

         Signin.setOnClickListener(v -> {
             if (validSecond == "OTP Verified")
             {
                 //Username and phone number should be stored after clicking this button
                 Intent redirect_home = new Intent(SigninPage.this,Sign_Up.class );
                 startActivity(redirect_home);
             }
             else
             {
                 Toast.makeText(this, "OTP not Verified or Wrong Otp", Toast.LENGTH_SHORT).show();
             }

         });

    }
}