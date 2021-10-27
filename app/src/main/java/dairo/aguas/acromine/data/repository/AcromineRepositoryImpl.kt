package dairo.aguas.acromine.data.repository

import dairo.aguas.acromine.data.endpoints.AcromineAPI
import dairo.aguas.acromine.domain.exception.DataNotFound
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.Result
import dairo.aguas.acromine.domain.repository.AcromineRepository
import dairo.aguas.acromine.domain.repository.DomainExceptionRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
class AcromineRepositoryImpl(
    private val acromineAPI: AcromineAPI,
    private val domainExceptionRepository: DomainExceptionRepository
) : AcromineRepository {

    override fun getAbbreviationDefinitions(abbreviation: String) = flow<Result<LongForms>> {
        val apiResult = acromineAPI.getAbbreviationDefinitions(abbreviation)
        if (apiResult.isNotEmpty()) {
            emit(Result.Success(apiResult.first().toDomainLongForms()))
        } else {
            emit(Result.Failure(DataNotFound))
        }
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}