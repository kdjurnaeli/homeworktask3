package com.example.homeworktask3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<CatViewHolder> {

    Context context;
    ArrayList<Cat> cats_favourite;

    public FavouriteAdapter(ArrayList<Cat> favouriteCats) {
        this.cats_favourite = favouriteCats;
    }

    @Override
    public CatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position){
        final Cat catAtPosition = cats_favourite.get(position);
        System.out.println("Favourite Cat List: "  +catAtPosition.getName()+" "+ catAtPosition.getId());
        holder.name_cat.setText(catAtPosition.getName());
        holder.cat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CatDetailActivity.class);
                System.out.println(catAtPosition.getId());
                intent.putExtra("catID",catAtPosition.getId());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount(){
        return cats_favourite.size();
    }

    public void setData(ArrayList<Cat> newFavCats) {
        this.cats_favourite = newFavCats;
    }

}

