package br.com.workshop.networking101

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import br.com.workshop.networking101.utilities.NetworkUtils
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val searchFieldEditText by lazy { findViewById<EditText>(R.id.search_field) }
    private val searchURLTextView by lazy { findViewById<TextView>(R.id.search_url_textview) }
    private val searchResultsTextView by lazy { findViewById<TextView>(R.id.search_results_textview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //DONE - 9 Sobrescreva os callbacks de menu e chamar este método ao clicar no item de busca no menu.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemThatWasClickedId = item.itemId
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    

    //DONE - 8 Criar um método para executar a AsyncTask, utilizando o texto digitado no searchFieldEditText, para gerar uma URL.
    // e exibir a URL criada com os parametros de busca no searchURLTextView.
    private fun makeGithubSearchQuery() {
        val githubQuery = searchFieldEditText.text.toString()
        val githubSearchUrl = NetworkUtils.buildUrl(githubQuery)
        githubSearchUrl?.let {
            searchURLTextView.text = it.toString()
            GithubQueryTask().execute(it)
        }
    }

    //DONE - 6 Criar uma AsyncTask para realizar as requisições à API do Github.
    //DONE - 7 Ao término da execução, setar no searchResultsTextView o retorno da requisição, caso o resultado seja válido.
    inner class GithubQueryTask : AsyncTask<URL, Void, String>() {

        override fun doInBackground(vararg params: URL): String? {
            val searchUrl = params[0]
            return try {
                NetworkUtils.getResponseFromHttpUrl(searchUrl)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(githubSearchResults: String?) {
            if (!githubSearchResults.isNullOrBlank()) {
                searchResultsTextView.text = githubSearchResults
            }
        }
    }

}
