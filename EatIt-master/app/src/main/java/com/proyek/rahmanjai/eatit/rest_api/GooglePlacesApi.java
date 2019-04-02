package com.proyek.rahmanjai.eatit.rest_api;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.proyek.rahmanjai.eatit.R;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GooglePlacesApi {

    public final static int SEARCH_RADIUS = 10000;
    public static final int TYPE_RESTAURANT = 0;
    public static final int TYPE_CAFE = 1;
    public static final int TYPE_NIGHT_CLUB = 2;
    public static final int RANKBY_PROMINENCE = 0;
    public static final int RANKBY_DISTANCE = 1;
    public static String WEB_KEY;
    Context ctx;

    public GooglePlacesApi(Context ctx) {
        GooglePlacesApi.this.ctx = ctx;
        WEB_KEY = ctx.getString(R.string.google_maps_web_key);
    }

    public RestaurantListClient getrestaurantlistclient() {
        String BASE_URL = "https://maps.googleapis.com/maps/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestaurantListClient.class);
    }

    public String getTypeString(int type) {
        switch (type) {
            case TYPE_RESTAURANT:
                return "restaurant";
            case TYPE_CAFE:
                return "cafe";
            case TYPE_NIGHT_CLUB:
                return "night_club";
        }
        return "";
    }

    public int getType(String s) {
        switch (s) {
            case "restaurant":
                return TYPE_RESTAURANT;
            case "cafe":
                return TYPE_CAFE;
            case "night_club":
                return TYPE_NIGHT_CLUB;
        }

        return TYPE_RESTAURANT;
    }

    public int getRank(String s) {
        switch (s) {
            case "Prominence":
                return RANKBY_PROMINENCE;
            case "Distance":
                return RANKBY_DISTANCE;
        }

        return RANKBY_PROMINENCE;
    }

   /* public String getRankString(int rank){
        switch (rank){
            case RANKBY_PROMINENCE:
                return "Prominence";
            case RANKBY_DISTANCE:
                return "Distance";
        }
        return "";
    }*/

    public HashMap<String, String> getQueryParams(LatLng loc, int type, int rankby) {
        HashMap<String, String> params = new HashMap<>();
        params.put("key", WEB_KEY);

        String latlng = loc.latitude + "," + loc.longitude;
        params.put("location", latlng);

        switch (type) {
            case TYPE_RESTAURANT:
                params.put("type", "restaurant");
                break;
            case TYPE_CAFE:
                params.put("type", "cafe");
                break;
            case TYPE_NIGHT_CLUB:
                params.put("type", "night_club");
                break;
        }

        switch (rankby) {
            case RANKBY_DISTANCE:
                params.put("rankby", "distance");
                break;
            case RANKBY_PROMINENCE:
                params.put("radius", String.valueOf(SEARCH_RADIUS));
                break;
        }

        return params;
    }
}
