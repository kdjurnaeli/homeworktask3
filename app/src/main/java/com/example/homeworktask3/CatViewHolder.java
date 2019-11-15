package com.example.homeworktask3;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CatViewHolder extends RecyclerView.ViewHolder {
    LinearLayout cat_linear;
    TextView name_cat;

    public CatViewHolder(View view){
        super(view);
        name_cat =view.findViewById(R.id.name_cat);
        cat_linear = view.findViewById(R.id.linearcat);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + name_cat.getText() + "'";
    }

}
