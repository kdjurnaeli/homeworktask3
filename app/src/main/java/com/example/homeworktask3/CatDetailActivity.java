package com.example.homeworktask3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class CatDetailActivity extends AppCompatActivity {
    Context context;
    TextView catName;
    ImageView catImage;
    TextView wikiLink;
    TextView dogFriend;
    TextView weight;
    ImageButton favouriteBtn;
    TextView description;
    TextView origin;
    TextView temper;
    TextView lifespan;
    Cat selectedCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail_activity);

        context = this;

        catName = findViewById(R.id.name_cat);
        catImage = findViewById(R.id.cat_image);
        favouriteBtn = findViewById(R.id.favourite_Btn);
        description = findViewById(R.id.cat_desc);
        origin = findViewById(R.id.origin);
        temper = findViewById(R.id.temper);
        lifespan = findViewById(R.id.lifeSpan);
        dogFriend = findViewById(R.id.dogF);
        weight = findViewById(R.id.weight);
        wikiLink = findViewById(R.id.wikiLink);

        Intent intent = getIntent();
        final String intentDetails = intent.getStringExtra("catID");

        catName.setText(selectedCat.getName());
        description.setText(selectedCat.getDescription());
        origin.setText("Origin: "+selectedCat.getOrigin());
        lifespan.setText("Lifespan: "+selectedCat.getLife_span());
        weight.setText("Weight: "+selectedCat.getWeight().getMetric());
        temper.setText("Temperament: \n"+selectedCat.getTemperament());
        dogFriend.setText("Dog Friendliness: "+selectedCat.getDog_friendly());
        wikiLink.setText("Wikipedia Link: \n"+selectedCat.getWikiUrl());

        String key = "034a1eec-9fa1-4cf2-9231-7265c68d27b1";
        String url = "https://api.thecatapi.com/v1/images/search?api_key="+key+"&breed_id="+intentDetails;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                APICat currentApi = APICat.(0);
                selectedCat = currentApi.getBreeds().get(0);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<>>(){}.getType();
                ArrayList<APICat> catAPI =gson.fromJson(response, collectionType);
                requestQueue.stop();


                if (FavouriteDatabase.favouriteCatsMap.containsKey(intentDetails)){
                    favouriteBtn.setTag(true);
                    favouriteBtn.setImageResource(R.drawable.pressedstar);

                } else {
                    favouriteBtn.setImageResource(R.drawable.unpressedstar);
                    favouriteBtn.setTag(false);
                }


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("GSON VOLLEY ERROR !");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void onToggleStar(View view){
        if (favouriteBtn.getTag().equals(true)){
            FavouriteDatabase.removeFromFavourite(selectedCat);
            favouriteBtn.setTag(false);
            favouriteBtn.setImageResource(R.drawable.unpressedstar);
        } else {
            FavouriteDatabase.addToFavourites(selectedCat);
            favouriteBtn.setTag(true);
            favouriteBtn.setImageResource(R.drawable.pressedstar);
        }

    }
}
