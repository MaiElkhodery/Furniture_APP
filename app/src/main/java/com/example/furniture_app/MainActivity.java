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
    boolean isFirstLogin;
    boolean isFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isFirstTime = pref.getBoolean(FIRST_TIME_KEY, true);
        boolean isFirstLogin = pref.getBoolean(FIRST_LOGIN_KEY, true);
        if (isFirstTime){
            setContentView(R.layout.splash_screen_layout);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pref.edit().putBoolean(FIRST_TIME_KEY, false).apply();

                    SignupFragment signupFragment= SignupFragment.newInstance();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.login_signup_container, signupFragment).commit();
                    finish();
                }
            }, 3000);
        }else if(isFirstLogin){
            pref.edit().putBoolean(FIRST_LOGIN_KEY, false).apply();

            LoginFragment loginFragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_signup_container, loginFragment).commit();
        }else{
            HomeFragment homeFragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_signup_container,homeFragment)
                    .commit();
        }

    }

}