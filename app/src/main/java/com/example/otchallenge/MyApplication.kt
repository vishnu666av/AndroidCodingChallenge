package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.di.AppComponent
import com.example.otchallenge.di.ApplicationModule
import com.example.otchallenge.di.DaggerAppComponent
/**
 * Custom Application class that serves as the entry point for the app.
 * This class initializes the Dagger dependency injection framework
 * by creating the `AppComponent`, which is used to provide application-level
 * dependencies throughout the app.
 *
 * The `onCreate` method is overridden to set up the Dagger component
 * using the `ApplicationModule`, ensuring that all necessary dependencies
 * are properly injected before any activity or service is launched.
 */
class MyApplication : Application() {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder()
			.applicationModule(ApplicationModule(this)).build()
	}
}
