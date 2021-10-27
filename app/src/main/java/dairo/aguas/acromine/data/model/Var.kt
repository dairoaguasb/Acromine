package dairo.aguas.acromine.data.model


import com.squareup.moshi.Json

data class Var(
    @Json(name = "freq")
    val freq: Int,
    @Json(name = "lf")
    val lf: String,
    @Json(name = "since")
    val since: Int
)