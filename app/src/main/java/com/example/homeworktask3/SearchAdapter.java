package com.example.homeworktask3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<CatViewHolder> implements Filterable {
    private ArrayList<Cat> catsList;
    private ArrayList<Cat> mNewList;
    private Context c;

` `
    public SearchAdapter(ArrayList<Cat> catsList) {
        this.catsList = catsList;
        this.mNewList = new ArrayList<>();
    }

    @Override
    public CatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.c = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position){
        if (mNewList.isEmpty()){
            mNewList = catsList;
        }
        final Cat catAtPosition = mNewList.get(position);
        System.out.println("New List: "  +catAtPosition.getName()+" "+ catAtPosition.getId());

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
        if (mNewList.isEmpty()){
            return catsList.size();
        } else {
            return mNewList.size();
        }

    }

    public void setData(ArrayList<Cat> cats) {
        this.catsList = cats;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {
                    mNewList = catsList;
                } else {
                    ArrayList<Cat> filteredList = new ArrayList<>();
                    for (Cat cat : catsList) {
                        if (cat.getName().toLowerCase().contains(searchString)) {
                            filteredList.add(cat);
                        }
                    }

                    mNewList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mNewList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mNewList = (ArrayList<Cat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

