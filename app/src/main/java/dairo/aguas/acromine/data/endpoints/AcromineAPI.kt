package dairo.aguas.acromine.data.endpoints

import dairo.aguas.acromine.data.model.AbbreviationDTO
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
interface AcromineAPI {

    @GET(ABBREVIATION)
    suspend fun getAbbreviationDefinitions(@Query("sf") abbreviation: String): List<AbbreviationDTO>
}

private const val ABBREVIATION = "acromine/dictionary.py"