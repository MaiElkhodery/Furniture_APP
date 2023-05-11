package com.example.furniture_app;

import android.os.Bundle;
import android.util.Log;
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
import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements CategoricalProductsAdapter.CategoryAdapterInterface {

    FragmentHomeBinding homeBinding;
    public static ViewModel viewModel;
    CategoricalProductsAdapter adapter;
    NavController navController;

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

        initRecyclerView();
        onClickChair();
        onClickBed();
        onClickLamp();
        onClickSofa();
        onClickTable();
        onClickAll();
        search();
    }
    private void search(){
        homeBinding.searchButton.setOnClickListener(view -> {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment(homeBinding.searchEditText.getText().toString()));
        });
    }
    public void initRecyclerView(){
        RecyclerView recyclerView = homeBinding.categoriesContainer;
        adapter = new CategoricalProductsAdapter(this);
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

    private void onClickChair(){
        homeBinding.chairTextView.setOnClickListener(view -> {
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getProductOfType(FurnitureCategory.CHAIR.getName()).observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
                Log.d("chair",products.size()+"");
            });
        });
    }
    private void onClickTable(){
        homeBinding.tableTextView.setOnClickListener(view -> {
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getProductOfType(FurnitureCategory.TABLE.getName()).observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
                Log.d("table",products.size()+"");
            });
        });
    }
    private void onClickBed(){
        homeBinding.bedTextView.setOnClickListener(view -> {
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getProductOfType(FurnitureCategory.BED.getName()).observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
                Log.d("bed",products.size()+"");
            });
        });
    }
    private void onClickLamp(){
        homeBinding.lampTextView.setOnClickListener(view -> {
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getProductOfType(FurnitureCategory.LAMP.getName()).observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
                Log.d("lamp",products.size()+"");
            });
        });
    }
    private void onClickSofa(){
        homeBinding.sofaTextView.setOnClickListener(view -> {
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getProductOfType(FurnitureCategory.SOFA.getName()).observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
                Log.d("sofa",products.size()+"");
            });
        });
    }
    private void onClickAll(){
        homeBinding.allTextView.setOnClickListener(view -> {
            homeBinding.allTextView.setTextColor(getResources().getColor(R.color.brown_categories));
            homeBinding.tableTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.lampTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.bedTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.sofaTextView.setTextColor(getResources().getColor(R.color.black));
            homeBinding.chairTextView.setTextColor(getResources().getColor(R.color.black));
            viewModel.getAllProducts().observe(getViewLifecycleOwner(),products -> {
                adapter.setProducts(products);
            });
        });
    }

}