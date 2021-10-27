package dairo.aguas.acromine.data.repository.exception

import dairo.aguas.acromine.data.exception.HttpErrors.getHttpError
import dairo.aguas.acromine.domain.exception.CommonErrors
import dairo.aguas.acromine.domain.exception.DomainException
import dairo.aguas.acromine.domain.repository.DomainExceptionRepository
import retrofit2.HttpException

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
class ExceptionAcromineRepositoryImpl : CommonErrors(), DomainExceptionRepository {

    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            getHttpError(error)
        } else {
            manageException(error)
        }
    }
}