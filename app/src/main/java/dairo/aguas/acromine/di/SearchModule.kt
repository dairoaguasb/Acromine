package dairo.aguas.acromine.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.acromine.data.endpoints.AcromineAPI
import dairo.aguas.acromine.data.repository.AcromineRepositoryImpl
import dairo.aguas.acromine.data.repository.exception.ExceptionAcromineRepositoryImpl
import dairo.aguas.acromine.domain.repository.AcromineRepository
import dairo.aguas.acromine.domain.repository.DomainExceptionRepository
import dairo.aguas.acromine.domain.usecase.GetAbbreviationDefinitionsUseCase
import dairo.aguas.acromine.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun searchViewModelProvider(
        getAbbreviationDefinitionsUseCase: GetAbbreviationDefinitionsUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ) = SearchViewModel(
        getAbbreviationDefinitionsUseCase,
        coroutineDispatcher
    )

    @Provides
    @ViewModelScoped
    fun getAbbreviationDefinitionsUseCaseProvider(acromineRepository: AcromineRepository) =
        GetAbbreviationDefinitionsUseCase(acromineRepository)

    @Provides
    @ViewModelScoped
    fun acromineRepositoryProvider(
        acromineAPI: AcromineAPI,
        domainExceptionRepository: DomainExceptionRepository
    ): AcromineRepository =
        AcromineRepositoryImpl(acromineAPI, domainExceptionRepository)

    @Provides
    @ViewModelScoped
    fun acromineAPIProvider(retrofit: Retrofit): AcromineAPI =
        retrofit.create(AcromineAPI::class.java)

    @Provides
    @ViewModelScoped
    fun exceptionAcromineRepositoryProvider(): DomainExceptionRepository =
        ExceptionAcromineRepositoryImpl()
}