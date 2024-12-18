package web.helperClass

import org.openqa.selenium.WebElement

class HelperClassFunctions {

    val email = "testuser11111@gmail.com"
    val password = "Test123"

    fun isListEmpty(list: List<WebElement>?): Boolean {
        return list!!.isNotEmpty()
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