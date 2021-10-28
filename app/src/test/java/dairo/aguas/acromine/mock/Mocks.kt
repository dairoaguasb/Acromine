package dairo.aguas.acromine.mock

import dairo.aguas.acromine.data.model.AbbreviationDTO
import dairo.aguas.acromine.data.model.Lf
import dairo.aguas.acromine.data.model.Var
import dairo.aguas.acromine.domain.model.FullForm
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.VariationObjects

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
object Mocks {

    private val MOCK_VAR = Var(
        freq = 100,
        lf = "lf",
        since = 2001
    )

    private val MOCK_LF = Lf(
        freq = 200,
        lf = "lf",
        since = 2002,
        vars = mutableListOf(MOCK_VAR)
    )

    val MOCK_ABBREVIATION_DTO = AbbreviationDTO(
        lfs = mutableListOf(MOCK_LF),
        sf = "sf"
    )

    private val MOCK_VARIATION_OBJECTS = VariationObjects(
        freq = 100,
        lf = "lf",
        since = 1900
    )

    val MOCK_FULL_FORM = FullForm(
        freq = 100,
        lf = "lf",
        variationObjects = mutableListOf(MOCK_VARIATION_OBJECTS),
        since = 2000
    )

    val MOCK_LONG_FORMS = LongForms(
        fullForms = mutableListOf(MOCK_FULL_FORM),
        sf = "sf"
    )
}
