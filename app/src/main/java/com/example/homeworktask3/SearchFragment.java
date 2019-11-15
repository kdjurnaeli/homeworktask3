package com.example.homeworktask3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchFragment extends Fragment {

    SearchView catSearch;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    private CatDatabase db;

    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_fragment, container, false);
        final  RequestQueue requestQueue=new Volley(getActivity();
        String url = "https://api.thecatapi.com/v1/breeds?api_key=034a1eec-9fa1-4cf2-9231-7265c68d27b1";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Cat>>(){}.getType();
                Collection<Cat> catAPI =gson.fromJson(response, collectionType);
                requestQueue.stop();

                ArrayList<Cat> catsList = new ArrayList<>(catAPI);

                recyclerView = view.findViewById(R.id.searchRecycler);
                layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                searchAdapter = new SearchAdapter(catsList);
                recyclerView.setAdapter(searchAdapter);
                catSearch = view.findViewById(R.id.catSearch);
                search(catSearch);


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

        return view;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                System.out.println("submit");
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                System.out.println("change");
                return true;
            }
        });
    }
}
