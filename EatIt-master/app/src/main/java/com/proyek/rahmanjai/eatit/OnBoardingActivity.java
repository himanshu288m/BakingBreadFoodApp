package com.proyek.rahmanjai.eatit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;


public  class OnBoardingActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Menu", "Easily find the type of food you're craving",R.drawable.dinner);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Order", "Choose from 100's of restaurants with new spots added daily.",R.drawable.menu);

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Deliver", "Earn upto 30% each time you dine with linked cards in network.",R.drawable.scooter);



        ahoyOnboarderCard1.setBackgroundColor(R.color.transparent);

        ahoyOnboarderCard2.setBackgroundColor(R.color.transparent);

        ahoyOnboarderCard3.setBackgroundColor(R.color.transparent);




        List<AhoyOnboarderCard> pages = new ArrayList<>();



        pages.add(ahoyOnboarderCard1);

        pages.add(ahoyOnboarderCard2);

        pages.add(ahoyOnboarderCard3);







            ahoyOnboarderCard1.setTitleColor(R.color.black);
            ahoyOnboarderCard1.setDescriptionColor(R.color.black);
            ahoyOnboarderCard1.setTitleTextSize(dpToPixels(20, this));
            ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(10, this));
            ahoyOnboarderCard1.setIconLayoutParams(142, 142, 308, 135,130,330);


        ahoyOnboarderCard2.setTitleColor(R.color.black);
        ahoyOnboarderCard2.setDescriptionColor(R.color.black);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(20, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(10, this));
        ahoyOnboarderCard2.setIconLayoutParams(140, 140, 280, 136,130,330);


        ahoyOnboarderCard3.setTitleColor(R.color.black);
        ahoyOnboarderCard3.setDescriptionColor(R.color.black);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(20, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(10, this));
        ahoyOnboarderCard3.setIconLayoutParams(140, 140, 280, 134,130,330);




        setFinishButtonTitle("Get Started");


        showNavigationControls(true);

        //setGradientBackground();
        setImageBackground(R.drawable.onboard);



      setInactiveIndicatorColor(R.color.grey_600);

        setActiveIndicatorColor(R.color.white);
      setOnboardPages(pages);

    }
    @Override

    public void onFinishButtonPressed() {

        Intent intent = new Intent(OnBoardingActivity.this,MainActivity.class);
        startActivity(intent);

    }

}
