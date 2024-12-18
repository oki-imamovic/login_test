package web.helperClass

import kotlin.random.Random

class HelperClassFunctions {

    private val alphaNum = ('0'..'9') + ('A'..'Z') + ('a'..'z')
    val email = "testuser11111@gmail.com"
    val password = "Test123"
    val baseURL = "https://thinking-tester-contact-list.herokuapp.com/"

    private fun generateRandomNumbers(): String {
        val digits = ('0'..'9')
        return (1..6)
            .map { digits.random() }
            .joinToString("")
    }

    private fun generateId(size: Int): String {
        return List(size) { Random.nextInt(0, alphaNum.size) }
            .map { alphaNum[it] }
            .joinToString(separator = "")
    }

    fun generateTestEmail(): String {
       val randomHex = generateId(3)
        return "email$randomHex@test.com".lowercase()
    }

    fun generateTestPassword(): String {
        val randomHex = generateId(5)
        return "Code$randomHex"
    }

    fun generateUserName(): String {
        val randomHex = generateId(6)
        return randomHex
    }

    fun generatePhoneNumber(): String {
        return "062${generateRandomNumbers()}"
    }
}