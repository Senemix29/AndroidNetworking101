package br.com.workshop.networking101.utilities

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

object NetworkUtils {

    private const val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
    private const val PARAM_QUERY = "q"
    private const val PARAM_SORT = "sort"
    private const val sortBy = "stars"

    //DONE - 1 Criar método para receber a string de busca e retornar um objeto do tipo URL
    //DONE - 2 Construa a URL utilizando as constantes de query parameters e a base url acima.
    fun buildUrl(githubSearchQuery: String): URL? {
        val builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build()

        return try {
            URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            null
        }
    }

    //DONE - 3 Criar método que receba uma URL e:
    //DONE - 4 abra uma conexão HTTP
    //DONE - 5 recupere o retorno da requisição e retorne como string
    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream = urlConnection.inputStream
            val scanner = Scanner(inputStream)
            scanner.useDelimiter("\\A")
            val hasInput = scanner.hasNext()
            return if (hasInput) scanner.next() else null
        } finally {
            urlConnection.disconnect()
        }
    }
}