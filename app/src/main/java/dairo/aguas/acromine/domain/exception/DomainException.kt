package dairo.aguas.acromine.domain.exception

import dairo.aguas.acromine.utils.Constants

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
open class DomainException(override val message: String = Constants.Values.EMPTY) : Throwable(message)
object NotFoundException : DomainException()
object BadRequestException : DomainException()
object InternalErrorException : DomainException()
object UnknownError : DomainException()
object NoConnectivityDomainException : DomainException()
object TimeOutException : DomainException()
object ParseException : DomainException()
data class HttpError(override val message: String) : DomainException()