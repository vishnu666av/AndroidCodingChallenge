package com.example.otchallenge.ui.books

import com.example.otchallenge.model.BookResults
/**
 * Interface representing the view in the MVP architecture for the Books feature.
 * It defines the contract for view-related operations that the presenter can invoke.
 * The `showLoading` method is used to indicate loading states to the user,
 * while `displayBooks` is responsible for presenting the retrieved list of books.
 * Additionally, `showError` provides a mechanism for displaying error messages
 * to the user, ensuring clear communication of the application's state.
 */
interface BooksView {
    fun showLoading(isLoading : Boolean)
    fun displayBooks(books: BookResults?)
    fun showError(errorMessage: String)
}