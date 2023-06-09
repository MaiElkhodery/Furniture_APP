package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    String PREF_NAME = "app_pref";
    String FIRST_LOGIN_KEY = "firstLogin";
    String FIRST_TIME_KEY = "firstTime";
    public NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
        initNavigation();
    }
    public void start(){
//        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
//        boolean isFirstTime = pref.getBoolean(FIRST_TIME_KEY, true);
//        boolean isFirstLogin = pref.getBoolean(FIRST_LOGIN_KEY, true);
//        if (isFirstTime){
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.mainActivityContainer, new SignupFragment()).commit();
//            pref.edit().putBoolean(FIRST_TIME_KEY, false).apply();
//        }else if(isFirstLogin){
//            pref.edit().putBoolean(FIRST_LOGIN_KEY, false).apply();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.mainActivityContainer, new LoginFragment()).commit();
//        }else{
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.mainActivityContainer, new HomeFragment())
//                    .commit();
//        }

    }
    public void initNavigation(){
        BottomNavigationView navigationBottom = findViewById(R.id.bottomNavigationView);
        NavHostFragment fragmentContainer = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);
        navController = fragmentContainer.getNavController();
        NavigationUI.setupWithNavController(navigationBottom, navController);
    }

}