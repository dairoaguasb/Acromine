package dairo.aguas.acromine.data.model

import com.squareup.moshi.Json

data class AbbreviationDTOItem(
    @Json(name = "lfs")
    val lfs: List<Lf>,
    @Json(name = "sf")
    val sf: String
)