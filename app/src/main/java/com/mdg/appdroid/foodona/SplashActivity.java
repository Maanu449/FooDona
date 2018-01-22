package com.mdg.appdroid.foodona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView quotes = findViewById(R.id.quotes);

        //creating the list of quotes to be randomly displayed each time app is launched
        String quotesList[] = {
                "This is IITR",
                "Shut up",
                "Swag of MDG",
                "Waste your time in reading this quote",
                "#Bakchodi"

        };

        //random number generator
        Random rand = new Random();

        //4 is the maximum and 0 is the minimum
        int  n = rand.nextInt(5);
        //displaying the quote at index 'n' in the text view
        String quote = quotesList[n];
        quotes.setText(quote);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer.
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Starts login activity
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                // close splash activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
