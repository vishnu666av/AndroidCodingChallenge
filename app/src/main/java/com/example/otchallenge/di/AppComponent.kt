package com.example.otchallenge.di

import com.example.otchallenge.ui.books.BooksListActivity
import com.example.otchallenge.usecase.GetBooksUseCase
import dagger.Component
import javax.inject.Singleton


/**
 * Dagger Component interface responsible for providing dependencies across the application.
 * It specifies the modules used for dependency injection (`ApplicationModule`, `AppModule`, and `NetworkModule`)
 * and declares the injection target (`BooksListActivity`).
 * Additionally, it exposes `getBooksUseCase` as a provision function for dependency retrieval, allowing it to be injected wherever required.
 */

@Singleton
@Component(modules = [ApplicationModule::class,AppModule::class, NetworkModule::class])
interface AppComponent {

	fun inject(activity: BooksListActivity)

	fun getBooksUseCase(): GetBooksUseCase

}
