package com.example.otchallenge.network

import com.example.otchallenge.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the API service for interacting with the New York Times Books API.
 * It includes a single function, `getBooksList`, which retrieves a list of hardcover fiction books.
 * This function is annotated with `@GET` to specify the endpoint and utilizes coroutines with `suspend`
 * for asynchronous execution. It takes the API key and an optional offset parameter for pagination,
 * returning a `Response` containing a `BookResponse` object that encapsulates the API response data.
 */
interface ApiService {

    @GET("lists/current/hardcover-fiction.json")
    suspend fun getBooksList(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int
    ): Response<BookResponse>
}