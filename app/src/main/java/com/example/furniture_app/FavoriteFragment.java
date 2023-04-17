package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding fragmentBinding;
    ViewModel viewModel;
    public static FavoriteListAdapter adapter ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = FragmentFavoriteBinding.inflate(inflater,container,false);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel=new ViewModel(getActivity().getApplication());
        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = fragmentBinding.favoritesContainer;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getAllFavProducts().observe(getViewLifecycleOwner(),products -> {
            adapter = new FavoriteListAdapter(products);
        });
        recyclerView.setAdapter(adapter);
    }
}