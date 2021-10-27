package dairo.aguas.acromine.domain.repository

import dairo.aguas.acromine.domain.exception.DomainException

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
interface DomainExceptionRepository {
    fun manageError(error: Throwable): DomainException
}