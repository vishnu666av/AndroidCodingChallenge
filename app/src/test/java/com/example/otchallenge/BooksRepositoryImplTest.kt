package com.example.otchallenge

import com.example.otchallenge.mock.createMockBookResponse
import com.example.otchallenge.network.ApiService
import com.example.otchallenge.repository.BooksRepositoryImpl
import com.example.otchallenge.utils.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class BooksRepositoryImplTest {

    private lateinit var repository: BooksRepositoryImpl
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mock(ApiService::class.java)
        repository = BooksRepositoryImpl(apiService)
    }

    @Test
    fun `getBooksList should return books when API call is successful`() = runBlocking {
        val mockBookResponse = createMockBookResponse()
        val mockResponse = Response.success(mockBookResponse)
        `when`(apiService.getBooksList(Constants.API_KEY, 0)).thenReturn(mockResponse)
        val result = repository.getBooksList()
        assert(result.isSuccess)
        assert(result.getOrNull() == mockResponse.body()?.results)
    }

    @Test
    fun `getBooksList should return failure when API call fails`() = runBlocking {
        `when`(apiService.getBooksList(Constants.API_KEY, 0)).thenThrow(RuntimeException("API error"))
        val result = repository.getBooksList()
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "API error")
    }

}