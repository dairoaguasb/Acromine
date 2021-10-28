package dairo.aguas.acromine.domain.usecase

import dairo.aguas.acromine.domain.exception.NoConnectivityDomainException
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.Result
import dairo.aguas.acromine.domain.model.getFailure
import dairo.aguas.acromine.domain.model.getSuccess
import dairo.aguas.acromine.domain.model.isFailure
import dairo.aguas.acromine.domain.model.isSuccess
import dairo.aguas.acromine.domain.repository.AcromineRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class GetAbbreviationDefinitionsUseCaseTest {

    private lateinit var getAbbreviationDefinitionsUseCase: GetAbbreviationDefinitionsUseCase
    private val acromineRepository = mockk<AcromineRepository>(relaxed = true)

    @Before
    fun setup() {
        getAbbreviationDefinitionsUseCase = GetAbbreviationDefinitionsUseCase(acromineRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnLongForms() = runBlocking {
        val longForms: LongForms = mockk()
        val flowLongForms = flowOf(Result.Success(longForms))

        coEvery {
            acromineRepository.getAbbreviationDefinitions(any())
        } returns flowLongForms

        val result = getAbbreviationDefinitionsUseCase.invoke("Smart")

        result.collect { resultLongForms ->
            assert(resultLongForms.isSuccess())
            assertEquals(resultLongForms.getSuccess(), longForms)
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnException() = runBlocking {
        val flowLongForms: Flow<Result<LongForms>> =
            flowOf(Result.Failure(NoConnectivityDomainException))

        coEvery {
            acromineRepository.getAbbreviationDefinitions(any())
        } returns flowLongForms

        val result = getAbbreviationDefinitionsUseCase.invoke("Smart")

        result.collect { resultLongForms ->
            assert(resultLongForms.isFailure())
            assertEquals(resultLongForms.getFailure(), NoConnectivityDomainException)
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnCatchException() = runBlocking {
        val flowLongForms = flow<Result<LongForms>> {
            throw NoConnectivityDomainException
        }

        coEvery {
            acromineRepository.getAbbreviationDefinitions(any())
        } returns flowLongForms

        val result = getAbbreviationDefinitionsUseCase.invoke("Smart")

        result.catch {
            assert(it is NoConnectivityDomainException)
        }.collect()
    }

    @After
    fun tearDown() {
        coVerify(exactly = 1) { acromineRepository.getAbbreviationDefinitions(any()) }
        confirmVerified(acromineRepository)
    }
}