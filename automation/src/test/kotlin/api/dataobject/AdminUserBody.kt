package api.dataobject

import com.fasterxml.jackson.annotation.JsonProperty

data class AdminUserBody(
    @JsonProperty("_id") val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    @JsonProperty("__v") val version: Int,
)
