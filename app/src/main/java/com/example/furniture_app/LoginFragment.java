package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.furniture_app.Database.User;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    public static String email;
    String password;
    ViewModel viewModel;
    FragmentLoginBinding loginBinding;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

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
        loginBinding.loginButton.setOnClickListener(view1 -> {
            email=loginBinding.emailEditText.getText().toString();
            password= loginBinding.passwordEditText.getText().toString();
            User user = viewModel.getUser(email);
            //startActivity( new Intent(getActivity(),HomeActivity.class));
//            if (user!=null && user.getPassword().equals(password)){
//                startActivity( new Intent(getActivity(),HomeActivity.class));
//            }else {
//                Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
//            }
        });
        loginBinding.signupTextView.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment);
        });
    }

}