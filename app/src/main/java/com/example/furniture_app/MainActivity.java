package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    String FIRST_TIME = "firstTime";
    boolean isFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        isFirstTime = pref.getBoolean(FIRST_TIME, true);
        if (isFirstTime){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setContentView(R.layout.splash_screen_layout);
                    finish();
                }
            }, 1000);
            setContentView(R.layout.activity_main);
            pref.edit().putBoolean(FIRST_TIME,false).apply();
            SignupFragment signupFragment= SignupFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_signup_container, signupFragment).commit();
        }else{
            LoginFragment loginFragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_signup_container, loginFragment).commit();
        }

    }

}