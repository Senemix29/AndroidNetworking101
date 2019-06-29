package br.com.workshop.networking101.utilities

import br.com.workshop.networking101.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApi {
    private const val GITHUB_BASE_URL = "https://api.github.com/"
    private const val AUTH_HEADER_KEY = "Authorization"
    private const val AUTH_TOKEN = "token 6be68c0ed3b3e7d1cb1a2d66c6e752635ce1632c"

    private lateinit var okHttpClient: OkHttpClient
    lateinit var searchService: GithubSearchService

    init {
        buildOkHttpClient()
        buildRetrofit()
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit
                .Builder()
                .baseUrl(GITHUB_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        searchService = retrofit.create(GithubSearchService::class.java)
    }

    private fun buildOkHttpClient() {
        val authInterceptor = Interceptor {
            val request = it
                    .request()
                    .newBuilder()
                    .addHeader(AUTH_HEADER_KEY, AUTH_TOKEN)
                    .build()
            it.proceed(request)
        }

        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = if (BuildConfig.DEBUG) BODY else NONE

        okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(logginInterceptor)
                .build()
    }

}