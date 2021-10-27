package dairo.aguas.acromine.domain.usecase

import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.Result
import dairo.aguas.acromine.domain.repository.AcromineRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
class GetAbbreviationDefinitionsUseCase(private val acromineRepository: AcromineRepository) {

    operator fun invoke(abbreviation: String): Flow<Result<LongForms>> =
        acromineRepository.getAbbreviationDefinitions(abbreviation)
}