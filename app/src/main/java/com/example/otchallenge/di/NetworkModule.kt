package com.example.otchallenge.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.otchallenge.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    /**
     * Provides a singleton instance of `OkHttpClient` configured with a cache and a network interceptor.
     * This client caches responses up to 5 MB, optimizing network usage based on connectivity.
     * When the device has network access, it allows responses to be cached for 5 seconds.
     * If there is no network, it serves cached responses that are up to 24 hours stale,
     * improving offline capabilities and user experience.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB Cache
        val cache = Cache(application.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(application)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24
                    ).build()
                chain.proceed(request)
            }
            .build()
    }

    /**
     * Provides a singleton instance of `Retrofit` configured for network requests.
     * This Retrofit client is set up with a base URL for the New York Times Books API
     * and uses the provided `OkHttpClient` for HTTP operations.
     * It also includes a Gson converter factory for JSON serialization and deserialization,
     * enabling seamless conversion between JSON responses and Kotlin data models.
     */

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /**
     * Provides a singleton instance of `ApiService`, which serves as the interface for making API calls.
     * This function uses the provided `Retrofit` instance to create the `ApiService` implementation,
     * enabling network requests to the New York Times Books API.
     * This allows for easy integration of API endpoints into the application.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    /**
     * Checks for network connectivity by accessing the system's connectivity services.
     * This function retrieves the active network information and returns `true` if the device is connected
     * to a network, or `false` otherwise. It helps determine the availability of network access
     * for making API requests and managing caching strategies accordingly.
     */
    private fun hasNetwork(application: Application): Boolean? {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}