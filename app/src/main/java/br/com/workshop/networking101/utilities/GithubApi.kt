package br.com.workshop.networking101.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApi {
    private const val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
    private var retrofit: Retrofit? = null
    const val PARAM_QUERY = "q"
    const val PARAM_SORT = "sort"
    const val SORT_BY_STARS = "stars"

    fun getInstance() = retrofit ?: buildRetrofit()

    private fun buildRetrofit() {
        retrofit = Retrofit
                .Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit?.create(GithubSearchService::class.java)
    }
}