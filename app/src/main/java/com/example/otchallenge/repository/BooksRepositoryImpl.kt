package com.example.otchallenge.repository

import com.example.otchallenge.model.BookResults
import com.example.otchallenge.network.ApiService
import com.example.otchallenge.utils.Constants.API_KEY

/**
 * Implementation of the `BooksRepository` interface, responsible for retrieving book data from the API.
 * This class uses the provided `ApiService` to make network requests to the New York Times Books API.
 * The `getBooksList` method fetches the list of books asynchronously, handling both successful responses
 * and errors. It returns a `Result` object that encapsulates either the retrieved `BookResults`
 * or an exception, allowing for robust error handling and ensuring that data fetching logic is
 * separated from the presentation layer, in line with the repository pattern.
 */
class BooksRepositoryImpl (private val apiService: ApiService) : BooksRepository {

    override suspend fun getBooksList(): Result<BookResults?> {
        return try {
            val response = apiService.getBooksList(API_KEY, 0)
            if (response.isSuccessful) {
                val books = response.body()?.results
                Result.success(books)
            } else {
                Result.failure(Exception("Failed to fetch data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}