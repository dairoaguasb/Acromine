package dairo.aguas.acromine.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.acromine.databinding.CardDetailBinding
import dairo.aguas.acromine.ui.model.VariationViewData

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class DetailViewHolder(private val cardDetailBinding: CardDetailBinding) :
    RecyclerView.ViewHolder(cardDetailBinding.root) {

    fun bind(variationViewData: VariationViewData) {
        cardDetailBinding.variationViewData = variationViewData
        cardDetailBinding.executePendingBindings()
    }
}