package dairo.aguas.acromine.data.repository

import dairo.aguas.acromine.data.endpoints.AcromineAPI
import dairo.aguas.acromine.data.model.AbbreviationDTO
import dairo.aguas.acromine.data.repository.exception.ExceptionAcromineRepositoryImpl
import dairo.aguas.acromine.domain.exception.DataNotFound
import dairo.aguas.acromine.domain.exception.NoConnectivityDomainException
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.getFailure
import dairo.aguas.acromine.domain.model.getSuccess
import dairo.aguas.acromine.domain.model.isFailure
import dairo.aguas.acromine.domain.model.isSuccess
import dairo.aguas.acromine.domain.repository.AcromineRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
class AcromineRepositoryImplTest {

    private lateinit var acromineRepository: AcromineRepository
    private val acromineAPI = mockk<AcromineAPI>(relaxed = true)

    @Before
    fun setup() {
        acromineRepository = AcromineRepositoryImpl(
            acromineAPI,
            ExceptionAcromineRepositoryImpl()
        )
    }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnLongForms() = runBlocking {
        val abbreviationDTO: AbbreviationDTO = mockk()
        val longForms: LongForms = mockk()

        coEvery {
            acromineAPI.getAbbreviationDefinitions(any())
        } returns mutableListOf(abbreviationDTO)

        every {
            abbreviationDTO.toDomainLongForms()
        } returns longForms

        val result = acromineRepository.getAbbreviationDefinitions("Smart")

        result.collect { resultLongForms ->
            assert(resultLongForms.isSuccess())
            assertEquals(resultLongForms.getSuccess(), longForms)
        }

        coVerify(exactly = 1) { acromineAPI.getAbbreviationDefinitions(any()) }
        verify(exactly = 1) { abbreviationDTO.toDomainLongForms() }

        confirmVerified(abbreviationDTO)
    }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnDataNotFound() = runBlocking {
        coEvery {
            acromineAPI.getAbbreviationDefinitions(any())
        } returns emptyList()

        val result = acromineRepository.getAbbreviationDefinitions("Smart")

        result.collect { resultLongForms ->
            assert(resultLongForms.isFailure())
            assertEquals(resultLongForms.getFailure(), DataNotFound)
        }

        coVerify(exactly = 1) { acromineAPI.getAbbreviationDefinitions(any()) }
    }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnException() = runBlocking {
        coEvery {
            acromineAPI.getAbbreviationDefinitions(any())
        } throws NoConnectivityDomainException

        val result = acromineRepository.getAbbreviationDefinitions("Smart")

        result.catch { resultLongForms ->
            assert(resultLongForms is NoConnectivityDomainException)
        }.collect()

        coVerify(exactly = 1) { acromineAPI.getAbbreviationDefinitions(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(acromineAPI)
    }
}