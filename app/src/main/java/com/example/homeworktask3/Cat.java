package com.example.homeworktask3;



import com.google.gson.annotations.SerializedName;

public class Cat {


    @SerializedName("life_span")
    String life_span;
    @SerializedName("dog_friendly")
    String dog_friendly;
    @SerializedName("wikipedia_url")
    String wikiUrl;
    String id;
    String name;
    String imageUrl;
    String description;
    Weight weight;
    String temperament;
    String origin;


    public boolean favourite;

    public Cat(String id, String name, String description, Weight weight, String temperament, String origin, String life_span, String dog_friendly, String wikiUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.dog_friendly = dog_friendly;
        this.wikiUrl = wikiUrl;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public Weight getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Cat{" + "name='" + name + '\'' + '}';
    }

    public class Weight{
        String metric;

        public Weight(String metric) {
            this.metric = metric;
        }

        public String getMetric() {
            return metric;
        }
    }
}
