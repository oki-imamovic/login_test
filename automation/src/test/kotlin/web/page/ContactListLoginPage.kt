package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import web.helperClass.HelperClassFunctions
import java.time.Duration

class ContactListLoginPage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    private val helperClassFunctions = HelperClassFunctions()

    @FindBy(id = "email")
    private val emailInputField: WebElement? = null

    @FindBy(id = "password")
    private val passwordInputField: WebElement? = null

    @FindBy(id = "submit")
    private val submitButton: WebElement? = null

    val isSubmitButtonDisplayed: Boolean
        get() {
            wait.until(ExpectedConditions.visibilityOf(submitButton))
            return submitButton!!.isDisplayed
        }

    fun logInWithExistingUser() {
        fillEmailField(helperClassFunctions.email)
        fillPasswordField(helperClassFunctions.password)
        clickOnSubmitButton()
    }

    fun clickOnSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(submitButton))
        submitButton!!.click()
    }

    fun fillEmailField(email: String) {
        wait.until(ExpectedConditions.visibilityOf(emailInputField))
        emailInputField!!.clear()
        emailInputField.sendKeys(email)
    }

    fun fillPasswordField(password: String) {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField))
        passwordInputField!!.clear()
        passwordInputField.sendKeys(password)
    }
}
