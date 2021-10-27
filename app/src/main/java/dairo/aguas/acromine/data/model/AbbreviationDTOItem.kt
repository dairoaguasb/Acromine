package dairo.aguas.acromine.data.model

import com.squareup.moshi.Json
import dairo.aguas.acromine.domain.model.LongForms

data class AbbreviationDTOItem(
    @Json(name = "lfs")
    val lfs: List<Lf>,
    @Json(name = "sf")
    val sf: String
) {
    fun toDomainLongForms(): LongForms {
        return LongForms(
            fullForms = this.lfs.map { it.toDomainFullForm() },
            sf = this.sf
        )
    }
}