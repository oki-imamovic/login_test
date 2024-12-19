package api.dataobject

data class LogInUserRequest(
    val email: String,
    val password: String,
)
