package com.example.furniture_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furniture_app.Database.ViewModel;
import com.example.furniture_app.databinding.FragmentFavoriteBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment{

    FragmentFavoriteBinding fragmentBinding;
    ViewModel viewModel;
    FavoriteListAdapter adapter;
    RecyclerView recyclerView;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
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
        deleteFavProduct();
        updateFavList();
    }

    public void initRecyclerView(){
        recyclerView = fragmentBinding.favoritesContainer;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new FavoriteListAdapter();
        recyclerView.setAdapter(adapter);
    }
    public void updateFavList(){
        viewModel.getAllFavProducts().observe(getViewLifecycleOwner(),products -> {
            adapter.setFavProductsList(products);
        });
    }
    public void deleteFavProduct(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                executorService.execute(() ->
//                        viewModel.deleteProduct(adapter.getProduct(viewHolder.getAdapterPosition())));
            }
        }).attachToRecyclerView(recyclerView);
    }
}