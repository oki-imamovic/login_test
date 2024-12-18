package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class UserSignUpPage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    @FindBy(id = "firstName")
    private val firstNameInput: WebElement? = null

    @FindBy(id = "lastName")
    private val lastNameInput: WebElement? = null

    @FindBy(id = "email")
    private val emailInput: WebElement? = null

    @FindBy(id = "password")
    private val passwordInput: WebElement? = null

    @FindBy(id = "submit")
    private val submitButton: WebElement? = null

    fun clickOnSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(submitButton))
        submitButton!!.click()
    }

    fun fillFirstName(firstName: String) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput))
        firstNameInput!!.click()
        firstNameInput.clear()
        firstNameInput.sendKeys(firstName)
    }

    fun fillLastNameInput(lastName: String) {
        lastNameInput!!.click()
        lastNameInput.clear()
        lastNameInput.sendKeys(lastName)
    }

    fun fillEmailInput(email: String) {
        emailInput!!.click()
        emailInput.clear()
        emailInput.sendKeys(email)
    }

    fun fillPasswordInput(password: String) {
        passwordInput!!.click()
        passwordInput.clear()
        passwordInput.sendKeys(password)
    }
}
