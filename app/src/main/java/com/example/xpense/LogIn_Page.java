package com.example.xpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class LogIn_Page extends AppCompatActivity {

    Button get_otp, new_user, sign_up;
    EditText phone_no, in_otp;
    String otp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //buttons
        get_otp = findViewById(R.id.get_otp);
        new_user = findViewById(R.id.new_user);
        sign_up = findViewById(R.id.sign_up_log);

        //edit text
        phone_no = findViewById(R.id.phone_no);
        in_otp = findViewById(R.id.otp);

        //firebase variable
        mAuth = FirebaseAuth.getInstance();

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn_Page.this,Register_Page.class));
                finish();
            }
        });

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone_no.getText().toString()))
                {
                    Toast.makeText(LogIn_Page.this, "Not a valid phone number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String number = phone_no.getText().toString();
                    sendVerificationCode(number);
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(in_otp.getText().toString()))
                {
                    Toast.makeText(LogIn_Page.this, "OTP is incorrect", Toast.LENGTH_SHORT).show();
                }
                else{
                    verifyCode(in_otp.getText().toString());
                }
            }
        });

    }



    private void sendVerificationCode(String number) {
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

            // sends sms from this method
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(LogIn_Page.this, "Verification Failed", Toast.LENGTH_SHORT).show();

            // Show a message and update the UI
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

            super.onCodeSent(s,token);
            otp = s;
            Toast.makeText(LogIn_Page.this, "Code Sent", Toast.LENGTH_SHORT).show();
            sign_up.setEnabled(true);
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
                    Toast.makeText(LogIn_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogIn_Page.this,bottom_navigation.class));
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
            startActivity(new Intent(LogIn_Page.this,bottom_navigation.class));
            finish();
        }
    }
}




