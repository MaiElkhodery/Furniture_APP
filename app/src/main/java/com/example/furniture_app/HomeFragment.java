package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.Product;
import com.example.furniture_app.Database.SharedViewModel;
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements CategoriesAdapter.CategoryAdapterInterface {

    FragmentHomeBinding homeBinding;
    public static ViewModel viewModel;
    CategoriesAdapter adapter;
    NavController navController;
    SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        sharedViewModel=MainActivity.sharedViewModel;
        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = homeBinding.categoriesContainer;
        adapter = new CategoriesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        viewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            adapter.setProducts(products);
        });

    }
    @Override
    public void onClick(Product product) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product));
    }

    @Override
    public void updateFavList() {
        viewModel.getAllFavProducts().observe(getViewLifecycleOwner(), products -> {
            sharedViewModel.setListOfFavProducts(products);
        });
    }

}