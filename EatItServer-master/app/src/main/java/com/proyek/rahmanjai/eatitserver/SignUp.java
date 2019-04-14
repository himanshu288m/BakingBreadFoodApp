package com.proyek.rahmanjai.eatitserver;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.proyek.rahmanjai.eatitserver.Common.Common;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyek.rahmanjai.eatitserver.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword,edtRestaurantName;
    Button btnSignUp,btnSelect;


    FirebaseStorage storage;
    StorageReference storageReference;

    Uri saveUri;

    String ImageUrl ;

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

        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Common.isConnectedToInternet(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("\n" +
                            "Please wait...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Cek apakah Nomot telepon telah terdaftar
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "Phone Number Registered !!", Toast.LENGTH_SHORT);
                            } else {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString(),edtPhone.getText().toString());                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Signup Successful !!", Toast.LENGTH_SHORT);
                                                            }
                        }



                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }

                    );

                    database.getReference("Restaurants").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            User user = new User(edtName.getText().toString(),edtPhone.getText().toString(),edtRestaurantName.getText().toString(),"https://firebasestorage.googleapis.com/v0/b/fastfooddelivery-fc7f3.appspot.com/o/images%2FRestaurant.jpeg?alt=media&token=2d5c5a2c-6fab-4fda-aa9e-0d4927758b6f");
                            database.getReference("Restaurants").child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Signup Successful !!", Toast.LENGTH_SHORT);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else {
                    Toast.makeText(SignUp.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
