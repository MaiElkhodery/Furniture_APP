package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.furniture_app.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    SharedPreferences sharedPreference;
    private final String sharedPrefName="register first time";
    private final String firstTimeToOpen="open first time";
    private boolean isFirstTimeToOpen;
    NavController navController;
    FragmentProfileBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreference=getContext().getSharedPreferences(sharedPrefName,0);
        isFirstTimeToOpen=sharedPreference.getBoolean(firstTimeToOpen,true);
        navController= Navigation.findNavController(view);
        if(isFirstTimeToOpen) {
            navController.navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment());
        }

    }
}