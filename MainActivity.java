package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Set time for splash screen
    private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting layout view//
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();

        } catch (NullPointerException e) {
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            //Create class for intent//
            public void run() {
                //creating intent
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}