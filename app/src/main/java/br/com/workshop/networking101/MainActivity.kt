package br.com.workshop.networking101

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val searchFieldEditText by lazy { findViewById<EditText>(R.id.search_field) }
    private val resultsRecyclerView by lazy { findViewById<RecyclerView>(R.id.results_recyclerview) }
    private lateinit var searchViewModel: SearchViewModel
    private var searchResultsAdapter = SearchResultsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.searchResultsLiveData.observe(this, Observer {
            searchResultsAdapter.results.clear()
            searchResultsAdapter.results.addAll(it.items)
            searchResultsAdapter.notifyDataSetChanged()
        })
        searchViewModel.errorLiveEvent.observe(this, Observer {

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.action_search) {
            searchViewModel.makeSearch(searchFieldEditText.text.toString())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        resultsRecyclerView.apply {
            adapter = searchResultsAdapter
        }
    }

}
