package dairo.aguas.acromine.data.exception

import com.google.gson.Gson
import dairo.aguas.acromine.domain.exception.BadRequestException
import dairo.aguas.acromine.domain.exception.DomainException
import dairo.aguas.acromine.domain.exception.HttpError
import dairo.aguas.acromine.domain.exception.InternalErrorException
import dairo.aguas.acromine.domain.exception.NotFoundException
import dairo.aguas.acromine.utils.Constants
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
object HttpErrors {
    private val httpErrors = mapOf(
        HttpsURLConnection.HTTP_BAD_REQUEST to BadRequestException,
        HttpsURLConnection.HTTP_NOT_FOUND to NotFoundException,
        HttpsURLConnection.HTTP_INTERNAL_ERROR to InternalErrorException
    )

    fun getHttpError(error: HttpException): DomainException {
        return if (httpErrors.containsKey(error.code())) {
            httpErrors.getValue(error.code())
        } else {
            HttpError(getMessage(error).message)
        }
    }

    private fun getMessage(exception: HttpException): DomainException {
        return try {
            var jsonString = exception.response()?.errorBody()?.string()
            if (jsonString.isNullOrEmpty()) jsonString = Constants.Values.JSON_EMPTY
            Gson().fromJson(jsonString, DomainException::class.java)
        } catch (exception_: Exception) {
            DomainException(Constants.Values.EMPTY)
        }
    }
}