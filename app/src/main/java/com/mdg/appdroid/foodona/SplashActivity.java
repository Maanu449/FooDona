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
    private static int SPLASH_TIME_OUT = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView quotes = findViewById(R.id.quotes);

        //creating the list of quotes to be randomly displayed each time app is launched
        String quotesList[] = {
                "Numbers dont matter whn it comes to giving !!!",
                "We make a living by what we get\nbut We make a life by what we give ...",
                "Imagine the smile you are creating ...",
                "Donating food is donating life ...",
                "Let the human in you come alive !!!"

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
