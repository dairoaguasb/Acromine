package dairo.aguas.acromine.domain.exception

import com.google.gson.JsonSyntaxException
import com.squareup.moshi.JsonDataException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
open class CommonErrors {

    fun manageException(throwable: Throwable): DomainException {
        return manageJavaErrors(throwable)
    }

    fun manageJavaErrors(throwable: Throwable): DomainException {
        return when (throwable) {
            is SocketTimeoutException -> TimeOutException
            is ConnectException -> InternalErrorException
            else -> manageParsingExceptions(throwable)
        }
    }

    fun manageParsingExceptions(throwable: Throwable): DomainException {
        return when (throwable) {
            is JsonDataException -> ParseException
            is JsonSyntaxException -> ParseException
            else -> manageOtherException(throwable)
        }
    }

    fun manageOtherException(throwable: Throwable): DomainException {
        return when (throwable) {
            is UnknownHostException -> NoConnectivityDomainException
            else -> UnknownError
        }
    }
}