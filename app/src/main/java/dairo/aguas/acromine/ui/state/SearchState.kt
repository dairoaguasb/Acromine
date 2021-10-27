package dairo.aguas.acromine.ui.state

import dairo.aguas.acromine.ui.model.SearchViewData

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
sealed class SearchState {

    object Empty : SearchState()

    object Loading : SearchState()

    class Success(val data: List<SearchViewData>) : SearchState()

    class Error(val resource: Int) : SearchState()
}
