package api.dataobject

data class AdminUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
