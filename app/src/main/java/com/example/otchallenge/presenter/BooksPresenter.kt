package com.example.otchallenge.presenter

import com.example.otchallenge.ui.books.BooksView
import com.example.otchallenge.usecase.GetBooksUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Presenter class responsible for handling the presentation logic for the Books feature.
 * It interacts with the `BooksView` to update the UI based on the data retrieved from the
 * `GetBooksUseCase`. The `loadBooks` function initiates the loading process, showing a loading
 * indicator while fetching book data in a background thread using coroutines.
 * Upon completion, it updates the view with the retrieved books or displays an error message
 * if the operation fails, maintaining a clear separation of concerns in the MVP architecture.
 */

class BooksPresenter(
    private val view: BooksView,
    private val getBooksUseCase: GetBooksUseCase,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun loadBooks() {
        view.showLoading(true)
        CoroutineScope(ioDispatcher).launch {
            val result = getBooksUseCase()
            withContext(mainDispatcher) {
                view.showLoading(false)
                result.onSuccess { books ->
                    view.displayBooks(books)
                }.onFailure { error ->
                    view.showError(error.message ?: "An unknown error occurred")
                }
            }
        }
    }
}