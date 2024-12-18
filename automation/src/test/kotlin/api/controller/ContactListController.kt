package api.controller

import api.dataobject.CreateUserResponse
import api.dataobject.UserRequestBody
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.http.ContentType
import web.helperClass.HelperClassFunctions

class UserController {

    private val helperClassFunctions = HelperClassFunctions()
    private val baseUrl = helperClassFunctions.baseURL

    private val objectMapper = jacksonObjectMapper()

    fun createUser(userRequestBody: UserRequestBody): CreateUserResponse? {
        val response: Response = RestAssured.given()
            .baseUri(baseUrl)
            .header("Authorization", "token")
            .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .body(userRequestBody)
            .post("/users")

        return if (response.statusCode == 200 || response.statusCode == 201) {
            objectMapper.readValue(response.body.asString())
        } else {
            println("Failed to create user: ${response.statusCode} - ${response.body.asString()}")
            null
        }
    }
}