package com.example.otchallenge

import com.example.otchallenge.mock.createMockBookResponse
import com.example.otchallenge.presenter.BooksPresenter
import com.example.otchallenge.ui.books.BooksView
import com.example.otchallenge.usecase.GetBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
@OptIn(ExperimentalCoroutinesApi::class)
class BooksPresenterTest {

    private lateinit var presenter: BooksPresenter
    private lateinit var view: BooksView
    private lateinit var getBooksUseCase: GetBooksUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        view = mock(BooksView::class.java)
        getBooksUseCase = mock(GetBooksUseCase::class.java)
        presenter = BooksPresenter(view, getBooksUseCase)
        presenter = BooksPresenter(view, getBooksUseCase, mainDispatcher = testDispatcher, ioDispatcher = testDispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `loadBooks should show loading and display books when successful`() = runBlocking {
        val mockBooks = createMockBookResponse().results
        `when`(getBooksUseCase()).thenReturn(Result.success(mockBooks))
        presenter.loadBooks()
        verify(view).showLoading(true)
        verify(view).displayBooks(mockBooks)
        verify(view).showLoading(false)
        verifyNoMoreInteractions(view)
    }
    @Test
    fun `loadBooks should show loading, display error on failure, and stop loading`() = runTest {
        val errorMessage = "Network error"
        `when`(getBooksUseCase()).thenReturn(Result.failure(Exception(errorMessage)))
        presenter.loadBooks()
        verify(view).showLoading(true)
        verify(view).showError(errorMessage)
        verify(view).showLoading(false)
        verifyNoMoreInteractions(view)
    }
}