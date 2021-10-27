package dairo.aguas.acromine.ui.state

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
sealed class SearchState {

    object Loading : SearchState()

    class Error(val resource: Int) : SearchState()
}
