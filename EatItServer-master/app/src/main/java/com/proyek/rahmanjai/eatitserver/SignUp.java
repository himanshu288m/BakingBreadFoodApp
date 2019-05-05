package com.proyek.rahmanjai.eatitserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.proyek.rahmanjai.eatitserver.Common.Common;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyek.rahmanjai.eatitserver.Model.Category;
import com.proyek.rahmanjai.eatitserver.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.UUID;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword,edtRestaurantName;
    Button btnSignUp,btnSelect , btnnupload;


    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri filePath;

    String ImageUrl ;
    private final int PICK_IMAGE_REQUEST = 71;
    ProgressDialog progressDialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRootRef = database.getReference();
    DatabaseReference restaurantRef = myRootRef.child("Restaurants");
    DatabaseReference table_user = myRootRef.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone =  findViewById(R.id.edtPhone);
        edtRestaurantName = findViewById(R.id.edtRestaurantName);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSelect = findViewById(R.id.btnSelect);
        btnnupload = findViewById(R.id.btnnupload);
        // Init Firebase


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Common.isConnectedToInternet(getBaseContext())) {


                    User user = new User(edtName.getText().toString(),edtPhone.getText().toString(),edtRestaurantName.getText().toString(),ImageUrl);
                    User user1 = new User(edtName.getText().toString(), edtPassword.getText().toString(),edtPhone.getText().toString());

                    restaurantRef.child(edtPhone.getText().toString()).setValue(user);
                            table_user.child(edtPhone.getText().toString()).setValue(user1);
                            Toast.makeText(SignUp.this, "Signup Successful !!", Toast.LENGTH_SHORT);
                            finish();

                }else {
                    Toast.makeText(SignUp.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            chooseImage();

            }
        });


btnnupload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        uploadImage();
    }
});
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();

        }
    }




    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            ImageUrl = downloadUrl.toString();
                            Toast.makeText(SignUp.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}

