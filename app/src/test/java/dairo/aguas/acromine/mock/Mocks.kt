package dairo.aguas.acromine.mock

import dairo.aguas.acromine.domain.model.FullForm
import dairo.aguas.acromine.domain.model.LongForms
import dairo.aguas.acromine.domain.model.VariationObjects

/**
 * Created by Dairo Aguas B on 28/10/2021.
 */
object Mocks {

    private val MOCK_VARIATION_OBJECTS = VariationObjects(
        freq = 100,
        lf = "lf",
        since = 1900
    )

    private val MOCK_FULL_FORM = FullForm(
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
