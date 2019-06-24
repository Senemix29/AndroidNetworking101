package br.com.workshop.networking101

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val searchFieldEditText by lazy { findViewById<EditText>(R.id.search_field) }
    private val searchURLTextView by lazy { findViewById<TextView>(R.id.search_url_textview) }
    private val searchResultsTextView by lazy { findViewById<TextView>(R.id.search_results_textview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //TODO - 6 Criar uma AsyncTask para realizar as requisições à API do Github.
    //TODO - 7 Ao término da execução, setar no searchResultsTextView o retorno da requisição, caso o resultado seja válido.
    //TODO - 8 Criar um método para executar a AsyncTask, utilizando o texto digitado no searchFieldEditText, para gerar uma URL.
    // e exibir a URL criada com os parametros de busca no searchURLTextView.
    //TODO - 9 Sobrescreva os callbacks de menu e chamar este método ao clicar no item de busca no menu.
}
