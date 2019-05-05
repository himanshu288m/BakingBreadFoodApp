package com.proyek.rahmanjai.eatit;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.proyek.rahmanjai.eatit.Model.PlaceList;
import com.proyek.rahmanjai.eatit.Model.SinglePlace;
import com.proyek.rahmanjai.eatit.Recycler.RestaurantListRecycler;
import com.proyek.rahmanjai.eatit.rest_api.GooglePlacesApi;
import com.proyek.rahmanjai.eatit.rest_api.RestaurantListClient;
import com.proyek.rahmanjai.eatit.utils.LoadingUtil;
import com.wang.avi.AVLoadingIndicatorView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantList extends AppCompatActivity {

    public static final String TAG = "list";
    RecyclerView recyclerHospital;
    ArrayList<SinglePlace> itemList;
    FrameLayout fader, listFrame;
    AVLoadingIndicatorView avi;
    TextView tvDisplayResult;
    GooglePlacesApi googlePlacesApi;
    RestaurantListClient hospitalListClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerHospital = (RecyclerView) findViewById(R.id.recyclerHospital);
        recyclerHospital.setLayoutManager(new LinearLayoutManager(this));

        fader = (FrameLayout) findViewById(R.id.fader);
        listFrame = (FrameLayout) findViewById(R.id.content_main);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        tvDisplayResult = findViewById(R.id.tvDisplayResult);


        stopLoadingAnimation();
        tvDisplayResult.setVisibility(View.INVISIBLE);





        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            Log.d(TAG, "onCreate: search started");

            setLoadingAnimation();
            String query = intent.getStringExtra(SearchManager.QUERY);

            toolbar.setTitle("Search results for '" + query + "'");

            googlePlacesApi = new GooglePlacesApi(getApplicationContext());
            hospitalListClient = googlePlacesApi.getrestaurantlistclient();

            HashMap<String, String> params = googlePlacesApi.getQueryParams(RestaurantLocation.curLocation, GooglePlacesApi.TYPE_RESTAURANT, GooglePlacesApi.RANKBY_PROMINENCE);
            params.put("radius", "5000");
            params.put("name", query);

            hospitalListClient.getNearbyRestaurant(params).enqueue(new Callback<PlaceList>() {
                @Override
                public void onResponse(Call<PlaceList> call, Response<PlaceList> response) {
//                    Log.d(TAG, "onResponse: resp received");
                    PlaceList placeList = response.body();

                    if (placeList != null) {
                        stopLoadingAnimation();
                        itemList = placeList.places;
                        if (itemList.size() == 0)
                            tvDisplayResult.setVisibility(View.VISIBLE);
                        else
                            bindRecyclerView();

                    }

                }

                @Override
                public void onFailure(Call<PlaceList> call, Throwable t) {
//                    Log.d(TAG, "onFailure: cannot access places api");
                    Toast.makeText(getApplicationContext(), "Unable to access server. Please try again later", Toast.LENGTH_SHORT).show();
                    tvDisplayResult.setVisibility(View.VISIBLE);
                }
            });
        } else {
            itemList = Parcels.unwrap(intent.getParcelableExtra("itemList"));
            bindRecyclerView();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    void bindRecyclerView() {
        RestaurantListRecycler hospitalListRecycler = new RestaurantListRecycler(itemList, this);
        recyclerHospital.setAdapter(hospitalListRecycler);
    }

    void setLoadingAnimation() {
        LoadingUtil.enableDisableView(listFrame, false);
        tvDisplayResult.setVisibility(View.INVISIBLE);
        fader.setVisibility(View.VISIBLE);
        avi.show();
    }

    void stopLoadingAnimation() {
        LoadingUtil.enableDisableView(listFrame, true);
        fader.setVisibility(View.GONE);
        avi.hide();
    }

}


