package com.proyek.rahmanjai.eatit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.proyek.rahmanjai.eatit.Common.Common;
import com.proyek.rahmanjai.eatit.Interface.ItemClickListener;
import com.proyek.rahmanjai.eatit.Model.Restaurant;
import com.proyek.rahmanjai.eatit.ViewHolder.RestaurantViewHolder;
import com.squareup.picasso.Picasso;



public class Restaurants extends AppCompatActivity {

    AlertDialog waitingDialog;
    RecyclerView recyclerView;
//    SwipeRefreshLayout mSwipeRefreshLayout ;


    FirebaseRecyclerOptions<Restaurant> options = new FirebaseRecyclerOptions.Builder<Restaurant>()
            .setQuery(FirebaseDatabase.getInstance()
                    .getReference()
                    .child("Restaurants"),Restaurant.class)
            .build();


    FirebaseRecyclerAdapter<Restaurant, RestaurantViewHolder>   adapter = new FirebaseRecyclerAdapter<Restaurant, RestaurantViewHolder>(options) {
        @Override
        public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restaurant_item,parent,false);
            return new RestaurantViewHolder(itemView);
        }

        @Override
        protected void onBindViewHolder(@NonNull RestaurantViewHolder viewHolder, int position, @NonNull Restaurant model) {


            viewHolder.txt_restaurant_name.setText(model.getName());
            Picasso.get().load(model.getImage())
                    .into(viewHolder.img_restaurant);
            final Restaurant clickItem = model;
            viewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClik) {
                    //Get CategoryId and Send to new activity
                    Intent intent = new Intent(Restaurants.this, Home.class);

                    Common.restaurantSelected = adapter.getRef(position).getKey();
                    adapter.notifyDataSetChanged();
                    startActivity(intent);
                    loadRestaurant();
                }
            });
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        recyclerView = findViewById(R.id.recycler_restaurant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRestaurant();
        if (Common.isConnectedToInternet(this)) {
            loadRestaurant();
        }else {
            Toast.makeText(Restaurants.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void loadRestaurant() {

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
//        adapter.notifyDataSetChanged();


    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//
//    }
}

