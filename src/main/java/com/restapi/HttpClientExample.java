package com.restapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HttpClientExample {

    public List<Data> bestInGenre(String genre, ApiResponse apiResponse){


        return apiResponse.data.stream()
                .min(Comparator.comparingDouble(Data::getImdb_rating).reversed().thenComparing(Data::getName))
             .filter(data -> data.genre!= null  && data.genre.toLowerCase().contains(genre.toLowerCase())).stream().toList();


    }


    public static void main(String[] args) throws IOException, InterruptedException {

        try(HttpClient client =  HttpClient.newHttpClient()){


            //request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/tvseries?page="))
                    .build();

            //response

            String response =  client.send(request,HttpResponse.BodyHandlers.ofString()).body();

            Gson gson =  new Gson();

            ApiResponse apiResponse = gson.fromJson(response,ApiResponse.class);

            //System.out.println(apiResponse.getData());

            HttpClientExample httpClientExample = new HttpClientExample();
            System.out.println(httpClientExample.bestInGenre("Drama",apiResponse));







        }catch (Exception _){

        }



    }

}