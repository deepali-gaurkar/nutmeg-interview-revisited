package com.restapi;

public class Data {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    Double imdb_rating;

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", imdb_rating=" + imdb_rating +
                ", genre='" + genre + '\'' +
                '}';
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(Double imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    String genre;


//    name": "Game of Thrones",
//            "runtime_of_series": "(2011-2019)",
//            "certificate": "A",
//            "runtime_of_episodes": "57 min",
//            "genre": "Action, Adventure, Drama",
//            "imdb_rating": 9.3,
//            "overview": "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
//            "no_of_votes": 1773458,
//            "id": 1
}
