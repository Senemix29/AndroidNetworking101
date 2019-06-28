package br.com.workshop.networking101

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private val searchFieldEditText by lazy { findViewById<EditText>(R.id.search_field) }
    private val searchURLTextView by lazy { findViewById<TextView>(R.id.search_url_textview) }
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.searchResultsLiveData.observe(this, Observer {

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

}
