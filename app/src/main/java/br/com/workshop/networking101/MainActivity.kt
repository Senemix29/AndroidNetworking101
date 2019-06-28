package br.com.workshop.networking101

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val searchFieldEditText by lazy { findViewById<EditText>(R.id.search_field) }
    private val resultsRecyclerView by lazy { findViewById<RecyclerView>(R.id.results_recyclerview) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }
    private val errorTextView by lazy { findViewById<TextView>(R.id.error_textview) }

    private lateinit var searchViewModel: SearchViewModel
    private var searchResultsAdapter = SearchResultsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.searchResultsLiveData.observe(this, Observer {
            showSuccessState()
            searchResultsAdapter.results.clear()
            searchResultsAdapter.results.addAll(it.items)
            searchResultsAdapter.notifyDataSetChanged()
        })

        searchViewModel.errorLiveEvent.observe(this, Observer {
            showErrorState()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.action_search) {
            showLoadingState()
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

    private fun showErrorState(){
        progressBar.visibility = GONE
        errorTextView.visibility = VISIBLE
        resultsRecyclerView.visibility = GONE
    }

    private fun showSuccessState(){
        progressBar.visibility = GONE
        errorTextView.visibility = GONE
        resultsRecyclerView.visibility = VISIBLE
    }

    private fun showLoadingState(){
        progressBar.visibility = VISIBLE
        errorTextView.visibility = GONE
        resultsRecyclerView.visibility = GONE
    }
}
