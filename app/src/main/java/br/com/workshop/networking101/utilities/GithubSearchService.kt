package br.com.workshop.networking101.utilities

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchService {
    @GET("search/repositories")
    fun searchRespositories(@Query(PARAM_QUERY) query: String,
                            @Query(PARAM_SORT) sortBy: String = SORT_BY_STARS): Call<SearchResult>

    companion object {
        const val PARAM_QUERY = "q"
        const val PARAM_SORT = "sort"
        const val SORT_BY_STARS = "stars"
    }
}