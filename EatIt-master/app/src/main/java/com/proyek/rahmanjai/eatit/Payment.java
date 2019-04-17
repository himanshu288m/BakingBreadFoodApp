package com.proyek.rahmanjai.eatit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyek.rahmanjai.eatit.Common.Common;
import com.proyek.rahmanjai.eatit.Model.Request;
import com.proyek.rahmanjai.eatit.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    TextView txtAmt , txtAddress;
    EditText edtcard , edtPwd ;
    Button btnPay , btnCancel ;

    com.rey.material.widget.CheckBox  ckbPickup , ckbCod ;

    String amount , address ;
    String SelfPickup = "Self PickUp" ;

    String order_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtAmt = findViewById(R.id.txtAmount);
        txtAddress = findViewById(R.id.txtAddress);

        edtcard = findViewById(R.id.edtCard);
        edtPwd = findViewById(R.id.edtPassword);

        btnCancel = findViewById(R.id.btnCancel);
        btnPay = findViewById(R.id.btnPay);

        ckbPickup = findViewById(R.id.ckbPickup);

        amount = getIntent().getStringExtra("CartAmount");
        address = getIntent().getStringExtra("Address");


        txtAmt.setText(amount);
        txtAddress.setText(address);

        order_number = getIntent().getStringExtra("order_number");
        Log.d("order_number",order_number);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Restaurants").child(Common.restaurantSelected).child("Requests");



        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if(edtcard.getText().toString().length()<=0  || edtPwd.getText().toString().length()<=0){
                    edtcard.setError("Invalid Card Number or Password");
                }
                else {

                    database.getReference("Restaurants").child(order_number).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(ckbPickup.isChecked()) {

                                    HashMap<String, Object> result = new HashMap<>();
                                    result.put("SelfPickup", SelfPickup);
                                    database.getReference("Restaurants").child(Common.restaurantSelected).child("Requests").child(order_number).updateChildren(result);
                                }

//                            Request user = new Request("Self PickUp");
//                            Log.d("Message","Value Inserted");
//                            database.getReference("Restaurants").child(Common.restaurantSelected).child("Requests").child(order_number).setValue(user);
////                            Log.d("Message1","Value Inserted1");


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    }


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Payment.this);
                    alertDialog.setTitle("PAYMENT SUCCESSFULL");
                    alertDialog.setMessage("Thankyou For Ordering "+"\n"+" Soon Your Order will be At DoorStep");
                   alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                           Intent Cancel = new Intent(Payment.this, Home.class);
                           Cancel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Cancel);

                       }
                   });
                   alertDialog.show();
                }




        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Cancel = new Intent(Payment.this, Cart.class);
                Cancel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(Cancel);
            }
        });


    }
}
