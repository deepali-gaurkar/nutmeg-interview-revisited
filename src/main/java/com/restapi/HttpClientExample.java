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
            ApiResponse api = gson.fromJson(response,ApiResponse.class);



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
               .max(Comparator.comparingDouble(Data::getImdb_rating).thenComparing(Data::getName))
               .map(Data::getName)
              .orElse("No names found");;

        System.out.println(string);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClientExample httpClientExample = new HttpClientExample();
        httpClientExample.bestInGenre("Thriller");

    }

}