package com.example.otchallenge.di

import com.example.otchallenge.network.ApiService
import com.example.otchallenge.repository.BooksRepository
import com.example.otchallenge.repository.BooksRepositoryImpl
import com.example.otchallenge.usecase.GetBooksUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Dagger Module that provides dependencies related to the application's domain layer.
 * This object module offers singleton instances of `BooksRepository` and `GetBooksUseCase`, ensuring
 * that these dependencies are shared across the application. The repository relies on `ApiService` for data
 * access, while `GetBooksUseCase` utilizes the repository to fetch book data as a use case.
 */
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideBooksRepository(apiService: ApiService): BooksRepository {
        return BooksRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetBooksUseCase(booksRepository: BooksRepository): GetBooksUseCase {
        return GetBooksUseCase(booksRepository)
    }
}