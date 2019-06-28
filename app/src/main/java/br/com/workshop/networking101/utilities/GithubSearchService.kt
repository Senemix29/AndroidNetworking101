package br.com.workshop.networking101.utilities

import br.com.workshop.networking101.utilities.GithubApi.PARAM_QUERY
import br.com.workshop.networking101.utilities.GithubApi.PARAM_SORT
import br.com.workshop.networking101.utilities.GithubApi.SORT_BY_STARS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchService {

    @GET
    fun searchRespositories(@Query(PARAM_QUERY) query: String,
                            @Query(PARAM_SORT) sortBy: String = SORT_BY_STARS): Call<SearchResult>
}