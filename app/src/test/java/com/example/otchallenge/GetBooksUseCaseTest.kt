package com.example.otchallenge

import com.example.otchallenge.mock.createMockBookResponse
import com.example.otchallenge.repository.BooksRepository
import com.example.otchallenge.usecase.GetBooksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetBooksUseCaseTest {

    private lateinit var getBooksUseCase: GetBooksUseCase
    private lateinit var booksRepository: BooksRepository
    @Before
    fun setup() {
        booksRepository = mock(BooksRepository::class.java)
        getBooksUseCase = GetBooksUseCase(booksRepository)
    }
    @Test
    fun `invoke should return books when repository returns success`() = runBlocking {
        val mockBooks = createMockBookResponse().results
        `when`(booksRepository.getBooksList()).thenReturn(Result.success(mockBooks))
        val result = getBooksUseCase()
        assert(result.isSuccess)
        assert(result.getOrNull() == mockBooks)
    }

    @Test
    fun `invoke should return failure when repository returns error`() = runBlocking {
        val errorMessage = "Error fetching books"
        `when`(booksRepository.getBooksList()).thenReturn(Result.failure(Exception(errorMessage)))
        val result = getBooksUseCase()
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == errorMessage)
    }
}