package br.com.workshop.networking101


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.workshop.networking101.utilities.GithubApi
import br.com.workshop.networking101.utilities.SearchResult
import com.hadilq.liveevent.LiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val searchResultsLiveData = MutableLiveData<SearchResult>()
    val errorLiveEvent = LiveEvent<Boolean>()
    fun makeSearch(query: String) {
        GithubApi.searchService.searchRespositories(query).enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                response.body()?.let {
                    searchResultsLiveData.value = it
                } ?: run {
                    errorLiveEvent.value = true
                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                errorLiveEvent.value = true
            }
        })
    }
}