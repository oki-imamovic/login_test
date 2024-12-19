package api.controller

import api.dataobject.*
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import web.helperClass.HelperClassFunctions

class ContactUserList {

    private val helperClassFunctions = HelperClassFunctions()
    private val baseUrl = helperClassFunctions.baseURL
    private val client = OkHttpClient()
    private val objectMapper = ObjectMapper()

    fun addContact(firstName: String, lastName: String, birthdate: String, email: String, phone: String, street1: String, street2: String, city: String, stateProvince: String, postalCode: String, country: String, token: String): ContactsUserBody? {
        val url = "$baseUrl/contacts"
        val jsonMediaType = "application/json".toMediaType()

        val requestBodyJson = objectMapper.writeValueAsString(ContactsUserBody(firstName = firstName, lastName = lastName, birthdate = birthdate, email = email, phone = phone, street1 = street1, street2 = street2, city = city, stateProvince = stateProvince, postalCode = postalCode, country = country))
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
                    objectMapper.readValue(responseBody, ContactsUserBody::class.java)
                }
            } else {
                println("Failed to create user: ${response.code} - ${response.body!!.string()}")
                null
            }
        }
    }

    fun getContactUser(token: String, id: String): ContactsUserBody? {
        val request = Request.Builder()
            .url("$baseUrl/contacts/$id")
            .addHeader("Authorization", token)
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw RuntimeException("Unexpected code $response")
            }
            return response.body!!.string().let { responseBody ->
                objectMapper.readValue(responseBody, ContactsUserBody::class.java)
            }
        }
    }

    fun patchContact(firstName: String, lastName: String, token: String, id: String): ContactsUserBody? {
        val url = "$baseUrl/contacts/$id"
        val jsonMediaType = "application/json".toMediaType()

        val updateFields = mapOf(
            "firstName" to firstName,
            "lastName" to lastName
        )
        val requestBodyJson = objectMapper.writeValueAsString(updateFields)
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
                    objectMapper.readValue(responseBody, ContactsUserBody::class.java)
                }
            } else {
                println("Failed to patch contact: ${response.code} - ${response.body!!.string()}")
                null
            }
        }
    }

    fun updateContact(firstName: String, lastName: String, birthdate: String, email: String, phone: String, street1: String, street2: String, city: String, stateProvince: String, postalCode: String, country: String, id: String, token: String): ContactsUserBody? {
        val url = "$baseUrl/contacts/$id"
        val jsonMediaType = "application/json".toMediaType()

        val requestBodyJson = objectMapper.writeValueAsString(ContactsUserBody(firstName = firstName, lastName = lastName, birthdate = birthdate, email = email, phone = phone, street1 = street1, street2 = street2, city = city, stateProvince = stateProvince, postalCode = postalCode, country = country))
        val requestBody = requestBodyJson.toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .header("Content-Type", "application/json")
            .put(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                response.body!!.string().let { responseBody ->
                    objectMapper.readValue(responseBody, ContactsUserBody::class.java)
                }
            } else {
                println("Failed to update user: ${response.code} - ${response.body!!.string()}")
                null
            }
        }
    }

    fun deleteContactUser(token: String, id: String): String {
        val request = Request.Builder()
            .url("$baseUrl/contacts/$id")
            .addHeader("Authorization", token)
            .delete()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw RuntimeException("Unexpected code $response")
            }
            return response.body!!.string()
        }
    }
}