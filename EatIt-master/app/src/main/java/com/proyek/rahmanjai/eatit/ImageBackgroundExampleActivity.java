package com.proyek.rahmanjai.eatit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class ImageBackgroundExampleActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Link Cards", "Sign up for free by activating your credit cards.", R.drawable.dinner);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Dine Out", "Choose from 100's of restaurants with new spots added daily.", R.drawable.menu);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Get Cashback", "Earn upto 30% each time you dine with linked cards in network.", R.drawable.scooter);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        //setGradientBackground();
        setImageBackground(R.drawable.onboard);

        ;
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
//        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);

    }
}
