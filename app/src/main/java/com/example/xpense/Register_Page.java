package com.example.xpense;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register_Page extends AppCompatActivity {
   
    EditText username,phoneNumber,otpPassword;
    Button  Sign,sendOtp;
    FirebaseAuth mAuth;
    String otp;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        
         username = findViewById(R.id.EnterUsername);
         phoneNumber = findViewById(R.id.EnterPhoneNumber);
         otpPassword = findViewById(R.id.OtpPassword);
         Sign = findViewById(R.id.buttonSignUp);
         sendOtp = findViewById(R.id.sendOTP);
         mAuth = FirebaseAuth.getInstance();

         
         sendOtp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (TextUtils.isEmpty(phoneNumber.getText().toString()) && TextUtils.isEmpty(username.getText().toString()))
                 {
                     Toast.makeText(Register_Page.this, "Not a valid phone number", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     String number = phoneNumber.getText().toString();
                     sendVerificationCode(number);
                 }
             }
         });

         Sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (TextUtils.isEmpty(otpPassword.getText().toString()))
                 {
                     Toast.makeText(Register_Page.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     verifyCode(otpPassword.getText().toString());
                 }

             }
         });

    }


    private void sendVerificationCode(String number)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+ number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if(code!=null)
            {
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Register_Page.this, "Verification Failed", Toast.LENGTH_SHORT).show();

            // Show a message and update the UI
        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {

            super.onCodeSent(s,token);
            otp = s;
            Toast.makeText(Register_Page.this, "Code Sent", Toast.LENGTH_SHORT).show();
            Sign.setEnabled(true);
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp,code);
        signInByCredentials(credential);
    }

    private void signInByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Register_Page.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_Page.this,bottom_navigation.class));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null)
        {
            startActivity(new Intent(Register_Page.this,bottom_navigation.class));
            finish();
        }
    }
}