package com.example.shashank.sha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.d("aaaaaaaaaaaaaa", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor=sharedPreferences.edit();
                boolean  firstTime=sharedPreferences.getBoolean("first", true);
                Log.d("aaaaaaaaaaaaaa", "llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

                if(firstTime) {
                    //editor.putBoolean("first",false);
                    //editor.commit();
                    Log.d("aaaaaaaaaaaaaa", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

                    Intent intent = new Intent(SplashScreen.this, BeforeMain1.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        },2000);
    }
}
