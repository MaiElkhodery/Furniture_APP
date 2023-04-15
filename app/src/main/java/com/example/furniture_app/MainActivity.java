package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    String PREF_NAME = "app_pref";
    String FIRST_TIME_KEY = "firstTime";
    String FIRST_LOGIN_KEY = "firstLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isFirstTime = pref.getBoolean(FIRST_TIME_KEY, true);
        boolean isFirstLogin = pref.getBoolean(FIRST_LOGIN_KEY, true);
        if (isFirstTime){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainActivityContainer,new SplashFragment()).commit();
            pref.edit().putBoolean(FIRST_TIME_KEY, false).apply();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainActivityContainer, SignupFragment.newInstance()).commit();
                    finish();
                }
            }, 5000);
        }else if(isFirstLogin){
            pref.edit().putBoolean(FIRST_LOGIN_KEY, false).apply();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainActivityContainer, LoginFragment.newInstance()).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainActivityContainer, HomeFragment.newInstance())
                    .commit();
        }

    }

}