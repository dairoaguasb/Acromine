package dairo.aguas.acromine.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.acromine.databinding.CardDefinitionBinding
import dairo.aguas.acromine.ui.adapter.DefinitionAdapter
import dairo.aguas.acromine.ui.model.SearchViewData

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
class DefinitionViewHolder(
    private val cardDefinitionBinding: CardDefinitionBinding,
    private val onListenerDefinitionAdapter: DefinitionAdapter.OnListenerDefinitionAdapter
) : RecyclerView.ViewHolder(cardDefinitionBinding.root) {

    fun bind(searchViewData: SearchViewData) {
        cardDefinitionBinding.searchViewData = searchViewData
        cardDefinitionBinding.clickListener = onListenerDefinitionAdapter
        cardDefinitionBinding.executePendingBindings()
    }
}