package dairo.aguas.acromine.ui.model

import dairo.aguas.acromine.domain.model.FullForm
import dairo.aguas.acromine.domain.model.VariationObjects
import java.io.Serializable

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
data class SearchViewData(
    val definitionName: String,
    val totalVariations: String,
    val freq: String,
    val since: String,
    val variations: List<VariationViewData>
) : Serializable {
    constructor(fullForm: FullForm) : this(
        definitionName = fullForm.lf,
        totalVariations = fullForm.variationObjects.size.toString(),
        freq = fullForm.freq.toString(),
        since = fullForm.since.toString(),
        variations = fullForm.variationObjects.map { VariationViewData(it) }
    )
}

data class VariationViewData(
    val freq: Int,
    val definitionName: String,
    val since: Int
) : Serializable {
    constructor(variationObjects: VariationObjects) : this(
        freq = variationObjects.freq,
        definitionName = variationObjects.lf,
        since = variationObjects.since
    )
}