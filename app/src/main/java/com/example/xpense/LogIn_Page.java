package com.example.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn_Page extends AppCompatActivity {

    Button get_otp, new_user, sign_up;
    EditText phone_no, in_otp;
    String temp;
    long phone;
    int temp_otp = 1234, otp;
    //https://developers.google.com/identity/sms-retriever/request sms verification link res
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        get_otp = findViewById(R.id.get_otp);
        new_user = findViewById(R.id.new_user);
        sign_up = findViewById(R.id.sign_up_log);
        phone_no = findViewById(R.id.phone_no);
        in_otp = findViewById(R.id.otp);
        get_otp.setOnClickListener(v -> {
            temp = phone_no.getText().toString();
            if (temp.length() != 10 )
            {
               Toast invalid_no = Toast.makeText(getApplicationContext(), "Enter a valid Number",Toast.LENGTH_SHORT);
                     invalid_no.show();
               /* LONG SYNTAX FOR TOAST
                Context context = getApplicationContext();
                CharSequence text = "Invalid Number";
                int duration = Toast.LENGTH_SHORT;
                Toast invalid_number = Toast.makeText(context, text, duration);
                invalid_number.show();**/
            }
            else
            {
                phone = Long.parseLong(temp);
                Toast otp_send = Toast.makeText(getApplicationContext(),"Otp send",Toast.LENGTH_SHORT);
                otp_send.show();
            }

        });

        sign_up.setOnClickListener(v -> {
            //temp_otp="1234";
            otp = Integer.parseInt(in_otp.getText().toString());
            if (otp == temp_otp)
            {
                Intent verified = new Intent(LogIn_Page.this,bottom_navigation.class );
                startActivity(verified);
            }
            else
            {
                Toast invalid_otp = Toast.makeText(getApplication(), "Invalid Otp", Toast.LENGTH_SHORT);
                invalid_otp.show();
            }


        });
        new_user.setOnClickListener(v -> {
            Intent redirect_home = new Intent(LogIn_Page.this, Register_Page.class );
            startActivity(redirect_home);
        });
        }
    }



