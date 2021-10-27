package dairo.aguas.acromine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dairo.aguas.acromine.databinding.CardDefinitionBinding
import dairo.aguas.acromine.ui.model.SearchViewData
import dairo.aguas.acromine.ui.viewholder.DefinitionViewHolder

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
class DefinitionAdapter : ListAdapter<SearchViewData, DefinitionViewHolder>(SearchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        return DefinitionViewHolder(
            CardDefinitionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object SearchDiffCallback : DiffUtil.ItemCallback<SearchViewData>() {
    override fun areItemsTheSame(oldItem: SearchViewData, newItem: SearchViewData): Boolean {
        return oldItem.definitionName == newItem.definitionName
    }

    override fun areContentsTheSame(oldItem: SearchViewData, newItem: SearchViewData): Boolean {
        return oldItem == newItem
    }
}