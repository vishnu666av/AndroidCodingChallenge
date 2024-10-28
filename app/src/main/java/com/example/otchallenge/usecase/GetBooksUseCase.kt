package com.example.otchallenge.usecase

import com.example.otchallenge.model.BookResults
import com.example.otchallenge.repository.BooksRepository
/**
 * Use case class that encapsulates the business logic for retrieving a list of books.
 * It acts as an intermediary between the presentation layer and the data layer, ensuring
 * a clear separation of concerns. The `invoke` operator function is used to initiate
 * the retrieval of book data from the `BooksRepository`, returning a `Result`
 * that contains either the list of books or an error. This structure facilitates
 * easier testing and maintenance of the application's logic.
 */
class GetBooksUseCase (private val booksRepository: BooksRepository) {

    suspend operator fun invoke(): Result<BookResults?> {
        return booksRepository.getBooksList()
    }
}