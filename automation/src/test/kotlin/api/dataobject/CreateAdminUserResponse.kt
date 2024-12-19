package api.dataobject

data class CreateAdminUserResponse(
    val user: AdminUserBody,
    val token: String,
)
