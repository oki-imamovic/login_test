package api.controller

import api.dataobject.CreateAdminUserResponse
import api.dataobject.AdminUserBody
import api.dataobject.LogInUserRequest
import api.dataobject.AdminUserRequest
import okhttp3.MediaType.Companion.toMediaType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.restassured.RestAssured
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import web.helperClass.HelperClassFunctions
import io.restassured.http.ContentType
import com.fasterxml.jackson.module.kotlin.readValue

class AdminUserList {

    private val helperClassFunctions = HelperClassFunctions()
    private val baseUrl = helperClassFunctions.baseURL
    private val client = OkHttpClient()
    private val objectMapper = jacksonObjectMapper()

    fun getCurrentUser(token: String): AdminUserBody? {
        val request = Request.Builder()
            .url("$baseUrl/users/me")
            .addHeader("Authorization", token)
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw RuntimeException("Unexpected code $response")
            }
            return response.body!!.string().let { responseBody ->
                objectMapper.readValue(responseBody, AdminUserBody::class.java)
            }
        }
    }

    fun createUser(firstName: String, lastName: String, email: String, password: String): CreateAdminUserResponse? {
            val response: io.restassured.response.Response? = RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", "token")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(AdminUserRequest(firstName = firstName, lastName = lastName, email = email, password = password))
                .post("/users")

            return if (response!!.statusCode == 200 || response.statusCode == 201) {
                objectMapper.readValue(response.body.asString())
            } else {
                println("Failed to create user: ${response.statusCode} - ${response.body.asString()}")
                null
            }
    }

    fun updateUser(firstName: String, lastName: String, email: String, password: String, token: String): AdminUserBody? {
        val url = "$baseUrl/users/me"
        val jsonMediaType = "application/json".toMediaType()

        val requestBodyJson = objectMapper.writeValueAsString(AdminUserRequest(firstName = firstName, lastName = lastName, email = email, password = password))
        val requestBody = requestBodyJson.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .header("Content-Type", "application/json")
            .patch(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                response.body!!.string().let { responseBody ->
                    objectMapper.readValue(responseBody, AdminUserBody::class.java)
                }
            } else {
                println("Failed to update user: ${response.code} - ${response.body!!.string()}")
                null
            }
        }
    }

    fun logInUser(email: String, password: String, token: String): CreateAdminUserResponse? {
        val url = "$baseUrl/users/login"
        val jsonMediaType = "application/json".toMediaType()

        val requestBodyJson = objectMapper.writeValueAsString(LogInUserRequest(email = email, password = password))
        val requestBody = requestBodyJson.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .header("Content-Type", "application/json")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                response.body!!.string().let { responseBody ->
                    objectMapper.readValue(responseBody, CreateAdminUserResponse::class.java)
                }
            } else {
                println("Failed to create user: ${response.code} - ${response.body!!.string()}")
                null
            }
        }
    }

    fun logOutUser(token: String): String {
        val request = Request.Builder()
            .url("$baseUrl/users/logout")
            .addHeader("Authorization", token)
            .post("".toRequestBody())
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw RuntimeException("Unexpected code $response")
            }
            return response.code.toString()
        }
    }

    fun deleteAdminUser(token: String): String {
        val request = Request.Builder()
            .url("$baseUrl/users/me")
            .addHeader("Authorization", token)
            .delete()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw RuntimeException("Unexpected code $response")
            }
            return response.code.toString()
        }
    }
}