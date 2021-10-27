package dairo.aguas.acromine.data.model

import com.squareup.moshi.Json
import dairo.aguas.acromine.domain.model.FullForm

data class Lf(
    @Json(name = "freq")
    val freq: Int,
    @Json(name = "lf")
    val lf: String,
    @Json(name = "since")
    val since: Int,
    @Json(name = "vars")
    val vars: List<Var>
) {
    fun toDomainFullForm(): FullForm {
        return FullForm(
            freq = this.freq,
            lf = this.lf,
            since = this.since,
            variationObjects = this.vars.map { it.toDomainVariationObjects() }
        )
    }
}