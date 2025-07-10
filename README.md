# Nutmeg Interview - Java API Practice

This project is a re-implementation of a REST API problem I encountered during an interview with Nutmeg. I struggled during the live interview, so I decided to revisit the problem in my own time using core Java, Gson, and HttpClient to strengthen my understanding of REST APIs, JSON parsing, and Java Streams.

The project fetches TV series data from a mock API and filters results based on IMDb ratings and genres.

### Technologies Used
- Java 17 (or 11+)
- HTTP Client (`java.net.http.HttpClient`)
- JSON Parsing with Gson
- Functional programming with Streams
- No frameworks (No Spring/Spring Boot)

### Features
- Fetches TV series data from `https://jsonmock.hackerrank.com/api/tvseries`
- Parses and maps the JSON response into Java objects using Gson
- Filters TV series with IMDb ratings ≥ 8.3
- Prints the sorted list to the console
