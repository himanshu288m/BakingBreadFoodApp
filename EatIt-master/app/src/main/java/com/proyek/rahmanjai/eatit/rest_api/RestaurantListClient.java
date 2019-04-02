package com.proyek.rahmanjai.eatit.rest_api;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */


import com.proyek.rahmanjai.eatit.Model.DetailResult;
import com.proyek.rahmanjai.eatit.Model.DistanceResult;
import com.proyek.rahmanjai.eatit.Model.PlaceList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface RestaurantListClient {

    @GET("place/nearbysearch/json?")
    Call<PlaceList> getNearbyRestaurant(@QueryMap Map<String, String> params);

    @GET("distancematrix/json?")
    Call<DistanceResult> getRestaurantDistances(@QueryMap Map<String, String> params);

    @GET("place/details/json?")
    Call<DetailResult> getRestaurantDetails(@QueryMap Map<String, String> params);
}
