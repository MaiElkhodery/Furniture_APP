package com.example.furniture_app;

import android.content.Intent;
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
    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

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
        signupBinding.signupButton.setOnClickListener(view1 -> {
            username=signupBinding.usernameEditText.getText().toString();
            email=signupBinding.emailEditText.getText().toString();
            password=signupBinding.passwordEditText.getText().toString();
            if (isValidEmail(email) && isValidPassword(password)) {
                User user = new User(email, username, password );
                viewModel.insertUser(user);
                startActivity( new Intent(getActivity(),HomeActivity.class));
            } else {
                Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
        signupBinding.loginTextView.setOnClickListener(view12 -> {
            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
        });
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 8;
    }
}