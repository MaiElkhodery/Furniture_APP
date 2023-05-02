package com.example.furniture_app;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.furniture_app.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    public static String email;
    String password;
    ViewModel viewModel;
    FragmentLoginBinding loginBinding;
    NavController navController;
    SharedPreferences sharedPreference;
    private final String sharedPrefName="register first time";
    private final String firstTimeToOpen="open first time";
    private boolean isFirstTimeToOpen;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =new ViewModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding=FragmentLoginBinding.inflate(inflater,container,false);
        return loginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=Navigation.findNavController(view);
        sharedPreference=getActivity().getSharedPreferences(sharedPrefName,0);

        loginBinding.loginButton.setOnClickListener(view1 -> {
            email=loginBinding.emailEditText.getText().toString();
            password= loginBinding.passwordEditText.getText().toString();
            User user = viewModel.getUser(email);
            if (user!=null && user.getPassword().equals(password)){
                sharedPreference.edit().putBoolean(firstTimeToOpen,false).apply();
                navController.popBackStack();
            }else {
                Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        loginBinding.signupTextView.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_loginFragment_to_signupFragment);
        });
    }

}