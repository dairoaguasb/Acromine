package dairo.aguas.acromine.ui.base

import androidx.lifecycle.ViewModel
import dairo.aguas.acromine.R
import dairo.aguas.acromine.domain.exception.BadRequestException
import dairo.aguas.acromine.domain.exception.DomainException
import dairo.aguas.acromine.domain.exception.InternalErrorException
import dairo.aguas.acromine.domain.exception.NoConnectivityDomainException
import dairo.aguas.acromine.domain.exception.ParseException
import dairo.aguas.acromine.domain.exception.TimeOutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
abstract class BaseViewModel<VS>(initialState: VS) : ViewModel() {

    protected val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<VS> get() = mutableState

    fun manageException(domainException: DomainException): Int =
        when (domainException) {
            is TimeOutException -> R.string.error_time_out
            is BadRequestException -> R.string.error_missing_params
            is InternalErrorException -> R.string.error_internal_error_exception
            is ParseException -> R.string.error_parsing_error
            is NoConnectivityDomainException -> R.string.error_internet_connection
            else -> R.string.error_some_wrong
        }
}
