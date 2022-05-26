package com.example.xpense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class NavigationHeader extends AppCompatActivity {

    private ImageView profile_pic;
    private TextView username,phone_number;

    private Uri imagePath;

    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_header);

        profile_pic = findViewById(R.id.profile_image);
        username = findViewById(R.id.username_dis);
        phone_number = findViewById(R.id.phone_number);



        username.setText(users.getUsername());

        phone_number.setText(users.getPhone_number());

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);

                //  uploadImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            imagePath = data.getData();
            getImageInImageView();
        }
    }

    //to display the image in profile_pic Image view using bitmap
    private void getImageInImageView() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        profile_pic.setImageBitmap(bitmap);
    }


    private void uploadImage() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("images/"+ UUID.randomUUID().toString()).putFile(imagePath)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful())
                                    {
                                        updateProfilePicture(task.getResult().toString());
                                    }
                                }
                            });
                            Toast.makeText(NavigationHeader.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(NavigationHeader.this, task.getException().getLocalizedMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    //this is for progress bar to pop up when loading the pic
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = 100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded/"+progress+"%");
                    }
                });
    }

    private void updateProfilePicture(String url)
    {
        FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance()
                .getCurrentUser().getUid()+"/profile_pic").setValue(url);
    }


}