package br.com.workshop.networking101.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApi {
    const val PARAM_QUERY = "q"
    const val PARAM_SORT = "sort"
    const val SORT_BY_STARS = "stars"
    private const val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
    lateinit var searchService: GithubSearchService
    init {
        buildRetrofit()
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit
                .Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        searchService = retrofit.create(GithubSearchService::class.java)
    }


}