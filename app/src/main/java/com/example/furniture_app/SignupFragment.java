package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.furniture_app.Database.User;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {
    String username;
    String email;
    String password;
    FragmentSignupBinding signupBinding;
    ViewModel viewModel;
    SharedPreferences sharedPreference;
    private final String sharedPrefName="register first time";
    private final String firstTimeToOpen="open first time";
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =new ViewModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signupBinding = FragmentSignupBinding.inflate(inflater,container,false);
        return signupBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreference=getActivity().getSharedPreferences(sharedPrefName,0);
        navController=Navigation.findNavController(view);
        signupBinding.signupButton.setOnClickListener(view1 -> {
            username=signupBinding.usernameEditText.getText().toString();
            email=signupBinding.emailEditText.getText().toString();
            password=signupBinding.passwordEditText.getText().toString();
            if (isValidEmail(email) && isValidPassword(password)) {
                User user = new User(email, username, password );
                viewModel.insertUser(user);
                sharedPreference.edit().putBoolean(firstTimeToOpen,false).apply();
                navController.popBackStack();
                navController.popBackStack();
            } else {
                Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
        signupBinding.loginTextView.setOnClickListener(view12 -> {
            navController.navigate(R.id.action_signupFragment_to_loginFragment);
        });
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 8;
    }
}