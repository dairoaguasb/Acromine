package dairo.aguas.acromine.ui.model

import dairo.aguas.acromine.domain.model.FullForm

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
data class SearchViewData(
    val definitionName: String,
    val totalVariations: String
) {
    constructor(fullForm: FullForm) : this(
        definitionName = fullForm.lf,
        totalVariations = fullForm.variationObjects.size.toString()
    )
}