package com.example.homeworktask3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesFragment extends Fragment {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    FavouriteAdapter favouriteAdapter;

    public FavouritesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_fragment, container, false);

        recyclerView = view.findViewById(R.id.favouriteRecycler);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        favouriteAdapter = new FavouriteAdapter(FavouriteDatabase.getFavouriteCats());
        recyclerView.setAdapter(favouriteAdapter);


        return view;


    }

}
