package dairo.aguas.acromine.data.model


import com.squareup.moshi.Json
import dairo.aguas.acromine.domain.model.VariationObjects

data class Var(
    @Json(name = "freq")
    val freq: Int,
    @Json(name = "lf")
    val lf: String,
    @Json(name = "since")
    val since: Int
) {
    fun toDomainVariationObjects(): VariationObjects {
        return VariationObjects(
            freq = this.freq,
            lf = this.lf,
            since = this.since
        )
    }
}