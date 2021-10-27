package dairo.aguas.acromine.domain.repository

import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
interface AcromineRepository {

    fun getAbbreviationDefinitions(abbreviation: String): Flow<Result<LongForms>>
}