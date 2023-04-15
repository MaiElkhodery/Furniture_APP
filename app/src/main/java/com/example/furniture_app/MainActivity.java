package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    String PREF_NAME = "app_pref";
    String FIRST_LOGIN_KEY = "firstLogin";
    String FIRST_TIME_KEY = "firstTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isFirstTime = pref.getBoolean(FIRST_TIME_KEY, true);
        boolean isFirstLogin = pref.getBoolean(FIRST_LOGIN_KEY, true);
        if (isFirstTime){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainActivityContainer, new SignupFragment()).commit();
            pref.edit().putBoolean(FIRST_TIME_KEY, false).apply();
        }else if(isFirstLogin){
            pref.edit().putBoolean(FIRST_LOGIN_KEY, false).apply();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainActivityContainer, new LoginFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainActivityContainer, new HomeFragment())
                    .commit();
        }

    }

}