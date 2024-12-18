package web.helperClass

import org.openqa.selenium.WebElement
import kotlin.random.Random

class HelperClassFunctions {

    private val ALPHA_NUMERIC = ('0'..'9') + ('A'..'Z') + ('a'..'z')
    val email = "testuser11111@gmail.com"
    val password = "Test123"
    val baseURL = "https://thinking-tester-contact-list.herokuapp.com/"

    fun isListEmpty(list: List<WebElement>?): Boolean {
        return list!!.isNotEmpty()
    }

    private fun generateId(): String {
        return List(3) { Random.nextInt(0, ALPHA_NUMERIC.size) }
            .map { ALPHA_NUMERIC[it] }
            .joinToString(separator = "")
    }

    fun generateTestEmail(): String {
       val randomHex = generateId()
        return "email$randomHex@test.com".lowercase()
    }

    fun isElementIncluded(element: String, listOfElements: List<WebElement>?): Boolean {
        for (webElement in listOfElements!!) {
            if (webElement.text.contains(element)) {
                return true
            }
        }
        return false
    }
}