package api.dataobject

data class UserRequestBody(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
