package dairo.aguas.acromine.data.repository.exception

import dairo.aguas.acromine.domain.exception.BadRequestException
import dairo.aguas.acromine.domain.exception.UnknownError
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class ExceptionAcromineRepositoryImplTest {

    private val httpException = mockk<HttpException>(relaxed = true)
    private val exceptionAcromineRepository = ExceptionAcromineRepositoryImpl()

    @Test
    fun whenHttpExceptionIs400ShouldReturnBadRequestException() {
        every { httpException.code() } answers { 400 }

        val domainException = exceptionAcromineRepository.manageError(httpException)

        assertEquals(domainException, BadRequestException)

        verify(exactly = 2) { httpException.code() }
        confirmVerified(httpException)
    }

    @Test
    fun whenManageErrorIsThrowableShouldReturnUnknownError() {
        val domainException = exceptionAcromineRepository.manageError(Throwable())

        assertEquals(domainException, UnknownError)
    }
}
