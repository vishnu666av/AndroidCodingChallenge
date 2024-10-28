package com.example.otchallenge.repository

import com.example.otchallenge.model.BookResults

/**
 * Interface defining the contract for the Books repository.
 * It provides a method, `getBooksList`, for fetching the list of books.
 * This method is a suspend function, allowing it to be called within coroutines,
 * and returns a `Result` containing `BookResults?`, enabling the handling
 * of both successful and failed responses in a type-safe manner.
 * This abstraction helps decouple the data source implementation from the rest of the application,
 * promoting a clean architecture.
 */

interface  BooksRepository {
    suspend fun getBooksList(): Result<BookResults?>
}