package br.com.workshop.networking101

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.workshop.networking101.utilities.SearchItem

class SearchResultsAdapter(var results: MutableList<SearchItem> = mutableListOf()) : RecyclerView.Adapter<SearchResultItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val viewInflated = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_search_result, parent, false)
        return SearchResultItemViewHolder(viewInflated)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        val searchItem = results[position]
        holder.bind(searchItem.name, searchItem.description)
    }
}

class SearchResultItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val repositoryNameTextView by lazy { itemView.findViewById<TextView>(R.id.repository_name) }
    private val repositoryDescriptionTextView by lazy { itemView.findViewById<TextView>(R.id.repository_description) }

    fun bind(repositoryName: String, repositoryDescription: String) {
        repositoryNameTextView.text = repositoryName
        repositoryDescriptionTextView.text = repositoryDescription
    }

}