package dairo.aguas.acromine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dairo.aguas.acromine.databinding.CardDetailBinding
import dairo.aguas.acromine.ui.model.VariationViewData
import dairo.aguas.acromine.ui.viewholder.DetailViewHolder

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class DetailAdapter() : ListAdapter<VariationViewData, DetailViewHolder>(VariationDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            CardDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object VariationDiffCallback : DiffUtil.ItemCallback<VariationViewData>() {
    override fun areItemsTheSame(oldItem: VariationViewData, newItem: VariationViewData): Boolean {
        return oldItem.definitionName == newItem.definitionName
    }

    override fun areContentsTheSame(
        oldItem: VariationViewData,
        newItem: VariationViewData
    ): Boolean {
        return oldItem == newItem
    }
}