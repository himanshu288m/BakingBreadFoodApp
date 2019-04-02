package com.proyek.rahmanjai.eatit.Model;

/**
 * Created by Rishabh Gupta on 29-03-2019
 */

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class Location {
    @SerializedName("lat")
    Double latitude;

    @SerializedName("lng")
    Double longitude;

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {
    }
}
