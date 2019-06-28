package br.com.workshop.networking101.utilities

import com.google.gson.annotations.SerializedName

data class SearchResult(@SerializedName("total_count") val totalCount: Int,
                        val items: List<SearchItem>)

data class SearchItem(val id: Int,
                      val name: String,
                      @SerializedName("full_name") val fullName: String,
                      val description: String)

