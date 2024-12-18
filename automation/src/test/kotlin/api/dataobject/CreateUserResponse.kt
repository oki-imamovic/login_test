package api.dataobject

data class CreateUserResponse(
    val user: User,
    val token: String,
)
