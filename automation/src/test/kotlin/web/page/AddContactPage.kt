package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class AddContactPage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    @FindBy(id = "submit")
    private val submitButton: WebElement? = null

    @FindBy(id = "firstName")
    private val firstName: WebElement? = null

    @FindBy(id = "lastName")
    private val lastName: WebElement? = null

    @FindBy(id = "birthdate")
    private val birthdate: WebElement? = null

    @FindBy(id = "email")
    private val email: WebElement? = null

    @FindBy(id = "phone")
    private val phone: WebElement? = null

    @FindBy(id = "street1")
    private val street1: WebElement? = null

    @FindBy(id = "city")
    private val city: WebElement? = null

    @FindBy(id = "stateProvince")
    private val stateProvince: WebElement? = null

    @FindBy(id = "postalCode")
    private val postalCode: WebElement? = null

    @FindBy(id = "country")
    private val country: WebElement? = null

    fun clickOnSubmitButton() {
        submitButton!!.click()
    }

    fun fillFirstNameInput(nameFirst: String) {
        wait.until(ExpectedConditions.visibilityOf(firstName))
        repeat(30) {
            firstName!!.clear()
            if (firstName.getAttribute("value").isEmpty()) return@repeat
        }
        firstName!!.sendKeys(nameFirst)
    }

    fun fillLastNameInput(nameLast: String) {
        wait.until(ExpectedConditions.visibilityOf(lastName))
        repeat(30) {
            lastName!!.clear()
            if (lastName.getAttribute("value").isEmpty()) return@repeat
        }
        lastName!!.sendKeys(nameLast)
    }

    fun fillDOBInput(dOB: String) {
        birthdate!!.click()
        birthdate.clear()
        birthdate.sendKeys(dOB)
    }

    fun fillEmailInput(eMail: String) {
        email!!.click()
        email.clear()
        email.sendKeys(eMail)
    }

    fun fillPhoneInput(number: String) {
        phone!!.click()
        phone.clear()
        phone.sendKeys(number)
    }

    fun fillStreetInput(street: String) {
        street1!!.click()
        street1.clear()
        street1.sendKeys(street)
    }

    fun fillCityInput(cityName: String) {
        city!!.click()
        city.clear()
        city.sendKeys(cityName)
    }

    fun fillProvinceInput(provinceState: String) {
        stateProvince!!.click()
        stateProvince.clear()
        stateProvince.sendKeys(provinceState)
    }

    fun fillPostalCodeInput(codePostal: String) {
        postalCode!!.click()
        postalCode.clear()
        postalCode.sendKeys(codePostal)
    }

    fun fillCountryInput(countryName: String) {
        country!!.click()
        country.clear()
        country.sendKeys(countryName)
    }
}