package dairo.aguas.acromine.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.acromine.ui.model.SearchViewData
import javax.inject.Inject

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    lateinit var searchViewData: SearchViewData
}