package dairo.aguas.acromine.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.acromine.domain.model.fold
import dairo.aguas.acromine.domain.usecase.GetAbbreviationDefinitionsUseCase
import dairo.aguas.acromine.ui.base.BaseViewModel
import dairo.aguas.acromine.ui.model.SearchViewData
import dairo.aguas.acromine.ui.state.SearchState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAbbreviationDefinitionsUseCase: GetAbbreviationDefinitionsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SearchState>(SearchState.Loading) {

    fun getAbbreviationDefinitions(abbreviation: String) {
        getAbbreviationDefinitionsUseCase.invoke(abbreviation).map { result ->
            result.fold(
                onSuccess = {
                    mutableState.value = SearchState.Success(
                        data = it.fullForms.map { fullForm ->
                            SearchViewData(fullForm)
                        }
                    )
                },
                onFailure = {
                    mutableState.value = SearchState.Error(manageException(it))
                }
            )
        }.onStart {
            mutableState.value = SearchState.Loading
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}