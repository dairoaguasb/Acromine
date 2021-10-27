package dairo.aguas.acromine.data.model

import com.squareup.moshi.Json

data class Lf(
    @Json(name = "freq")
    val freq: Int,
    @Json(name = "lf")
    val lf: String,
    @Json(name = "since")
    val since: Int,
    @Json(name = "vars")
    val vars: List<Var>
)