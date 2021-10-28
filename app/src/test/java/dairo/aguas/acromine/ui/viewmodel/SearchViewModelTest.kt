package dairo.aguas.acromine.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.acromine.R
import dairo.aguas.acromine.domain.exception.DataNotFound
import dairo.aguas.acromine.domain.exception.NoConnectivityDomainException
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.Result
import dairo.aguas.acromine.domain.usecase.GetAbbreviationDefinitionsUseCase
import dairo.aguas.acromine.mock.Mocks
import dairo.aguas.acromine.ui.state.SearchState
import dairo.aguas.acromine.utils.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel
    private val getAbbreviationDefinitionsUseCase =
        mockk<GetAbbreviationDefinitionsUseCase>(relaxed = true)

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(
            getAbbreviationDefinitionsUseCase,
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnSuccessState() =
        mainCoroutineRule.runBlockingTest {
            val flowFullForms = flowOf(Result.Success(Mocks.MOCK_LONG_FORMS))

            coEvery {
                getAbbreviationDefinitionsUseCase.invoke(any())
            } returns flowFullForms

            val result = arrayListOf<SearchState>()
            val jop = launch {
                searchViewModel.state.toList(result)
            }

            searchViewModel.getAbbreviationDefinitions("smart")

            assertEquals(result.size, 3)
            assertTrue(result[0] is SearchState.Empty)
            assertTrue(result[1] is SearchState.Loading)
            assertTrue(result[2] is SearchState.Success)

            jop.cancel()
        }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnDataNotFound() =
        mainCoroutineRule.runBlockingTest {
            val flowFullForms: Flow<Result<LongForms>> =
                flowOf(Result.Failure(DataNotFound))

            coEvery {
                getAbbreviationDefinitionsUseCase.invoke(any())
            } returns flowFullForms

            val result = arrayListOf<SearchState>()
            val jop = launch {
                searchViewModel.state.toList(result)
            }

            searchViewModel.getAbbreviationDefinitions("smart")

            assertEquals(result.size, 3)
            assertTrue(result[0] is SearchState.Empty)
            assertTrue(result[1] is SearchState.Loading)
            assertTrue(result[2] is SearchState.DataNotFound)

            jop.cancel()
        }

    @Test
    fun whenGetAbbreviationDefinitionsIsCalledShouldReturnFailure() =
        mainCoroutineRule.runBlockingTest {
            val flowFullForms: Flow<Result<LongForms>> =
                flowOf(Result.Failure(NoConnectivityDomainException))

            coEvery {
                getAbbreviationDefinitionsUseCase.invoke(any())
            } returns flowFullForms

            val result = arrayListOf<SearchState>()
            val jop = launch {
                searchViewModel.state.toList(result)
            }

            searchViewModel.getAbbreviationDefinitions("smart")

            assertEquals(result.size, 3)
            assertTrue(result[0] is SearchState.Empty)
            assertTrue(result[1] is SearchState.Loading)
            assertTrue(result[2] is SearchState.Error)
            assertTrue(
                (result[2] as SearchState.Error).resource == R.string.error_internet_connection
            )

            jop.cancel()
        }

    @After
    fun tearDown() {
        coVerify { getAbbreviationDefinitionsUseCase.invoke(any()) }
        confirmVerified(getAbbreviationDefinitionsUseCase)
    }
}