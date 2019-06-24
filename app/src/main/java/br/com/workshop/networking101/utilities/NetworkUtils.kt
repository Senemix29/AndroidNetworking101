package br.com.workshop.networking101.utilities

object NetworkUtils {

    private const val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
    private const val PARAM_QUERY = "q"
    private const val PARAM_SORT = "sort"
    private const val sortBy = "stars"

    //TODO - 1 Criar método para receber a string de busca e retornar um objeto do tipo URL
    //TODO - 2 Construa a URL utilizando as constantes de query parameters e a base url acima.

    //TODO - 3 Criar método que receba uma URL e:
    //TODO - 4 abra uma conexão HTTP
    //TODO - 5 recupere o retorno da requisição e retorne como string

}