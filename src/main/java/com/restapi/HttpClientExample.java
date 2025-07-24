package com.restapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class HttpClientExample {

    static class Data{

        String name;

        public Data() {

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String runtime_of_series;
        String genre;
        Double imdb_rating;

    }

    static class Api{
        int page;
        int per_page;
        List<Data> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public List<Data> getData() {
            return data;
        }


        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        int total;
        int total_pages;

    }

    private void bestInGenre(String genre) throws IOException, InterruptedException {

        int page=1;
        int total_pages=1;

        List<Data> allShows = new ArrayList<>();

        while(page<=total_pages){
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/tvseries?page="+page))
                    .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            Gson gson = new Gson();
            Api api = gson.fromJson(response,Api.class);



            total_pages=api.getTotal_pages();
            allShows.addAll(api.getData());


//            System.out.println(page);
//            System.out.println(total_pages);
//            String targetGenre = "comedy";// or "horror"

//            List<String> thriller = allShows.stream()
//                    .filter(data -> {
//                        if (data.getGenre() == null) return false;
//                        return Arrays.stream(data.getGenre().toLowerCase().split(","))
//                                .map(String::trim)
//                                .anyMatch(g -> g.equals(targetGenre));
//                    })
//                    .map(data -> data.getName() + " - " + data.getImdb_rating() + " - " + data.getGenre())
//                    .toList();
//
//            System.out.println("Thriller shows: " + thriller);

            page++;


        }


       String string=  allShows.stream()
                .filter(data -> data.genre!=null && data.genre.toLowerCase()
                .contains(genre.toLowerCase()))
               .min(Comparator.comparingDouble(Data::getImdb_rating).reversed().thenComparing(Data::getName))
               .map(Data::getName)
              .orElse("No names found");;

        System.out.println(string);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClientExample httpClientExample = new HttpClientExample();
        httpClientExample.bestInGenre("Comedy");

    }




}