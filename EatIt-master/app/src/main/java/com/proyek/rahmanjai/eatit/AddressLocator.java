package com.proyek.rahmanjai.eatit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddressLocator extends AppCompatActivity {
    public Button current_location_text,save;
    private LinearLayout address_layout;
    private AutocompleteSupportFragment autocompleteFragment;
    private PlacesClient placesClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_locator);
        getSupportActionBar().hide();

        Places.initialize(getApplicationContext(), "AIzaSyAWsle3RztVrkmcRPoxpRoPemn8ua35uos");

        current_location_text = findViewById(R.id.current_location_text);
        address_layout = findViewById(R.id.address_layout);
        address_layout.setVisibility(View.INVISIBLE);
        placesClient = Places.createClient(this);
        save = findViewById(R.id.button);

        getcurrentLocation();

        getFragment();

        current_location_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_layout.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddressLocator.this,RestaurantLocation.class);
                startActivity(intent);

            }
        });





    }


    public  void getcurrentLocation(){

        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME,Place.Field.ADDRESS);

        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.builder(placeFields).build();

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            placesClient.findCurrentPlace(request).addOnSuccessListener((new OnSuccessListener<FindCurrentPlaceResponse>() {
                @Override
                public void onSuccess(FindCurrentPlaceResponse response) {
                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                        /*Log.d(TAG, String.format("Place '%s' has likelihood: %f",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));*/
                        current_location_text.setText(String.format("%s",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getPlace().getAddress()
                        ));
                    }
                }
            })).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Toast.makeText(AddressLocator.this, "place not found" + apiException.getStatusCode(), Toast.LENGTH_SHORT).show();
                    }


                }
            });
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            Toast.makeText(AddressLocator.this,"please provide permissions",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        address_layout.setVisibility(View.INVISIBLE);
    }

    public void getFragment() {

        address_layout.setVisibility(View.INVISIBLE);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyAWsle3RztVrkmcRPoxpRoPemn8ua35uos");
        }
        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Enter a new address");
        autocompleteFragment.setCountry("IN");


// Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));


// Set up a PlaceSelectionListener to handle the response.


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {


            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                autocompleteFragment.setText(place.getName());
                address_layout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                autocompleteFragment.setText(status.getStatusMessage());
                address_layout.setVisibility(View.INVISIBLE);

            }
        });

    }


}

