package com.restapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HttpClientExample {

    public List<String> bestInGenre(String genre) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        List<Data> allShows = new ArrayList<>();

        int page =1;
        int total_pages = 1;

        while(page<=total_pages) {


            //request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/tvseries?page=" + page))
                    .build();

            //response

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();


            Gson gson = new Gson();

            ApiResponse apiResponse = gson.fromJson(response, ApiResponse.class);


            total_pages = apiResponse.getTotal_pages();
            allShows.addAll(apiResponse.data);

            page++;


        }


            return allShows.stream()
                    .filter(data -> data.genre != null && data.genre.toLowerCase().contains(genre.toLowerCase()))
                    .sorted(Comparator.comparingDouble(Data::getImdb_rating).reversed()
                            .thenComparing(Data::getName))
                    .map(Data::getName)
                    .toList();


    }


    public static void main(String[] args) throws IOException, InterruptedException {




            HttpClientExample httpClientExample = new HttpClientExample();
            System.out.println(httpClientExample.bestInGenre("Comedy"));




    }

}