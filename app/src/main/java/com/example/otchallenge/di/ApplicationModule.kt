package com.example.otchallenge.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Dagger Module that provides application-level dependencies.
 * This module offers a singleton instance of `Application`, allowing it to be injected wherever needed
 * within the application's dependency graph.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}