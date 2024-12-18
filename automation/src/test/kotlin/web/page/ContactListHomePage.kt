package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class ContactListHomePage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    @FindBy(id = "logout")
    private val logoutButton: WebElement? = null

    @FindBy(id = "add-contact")
    private val addContact: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[2]")
    private val tableName: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[3]")
    private val tableDOB: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[4]")
    private val tableEmail: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[5]")
    private val tablePhone: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[6]")
    private val tableAddress: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[7]")
    private val tableCity: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[8]")
    private val tableCountry: WebElement? = null

    val getTableDOBText: String get() = tableDOB!!.text
    val getTableEmailText: String get() = tableEmail!!.text
    val getTPhoneText: String get() = tablePhone!!.text
    val getTAddressText: String get() = tableAddress!!.text
    val getTCityText: String get() = tableCity!!.text
    val getTCountryText: String get() = tableCountry!!.text

    val isLogOutButtonDisplayed: Boolean
        get() {
            wait.until(ExpectedConditions.visibilityOf(logoutButton))
            return logoutButton!!.isDisplayed
        }

    val isNamePresentInTableList: String
        get() {
        wait.until(ExpectedConditions.visibilityOf(tableName))
        return tableName!!.text
    }

    fun clickOnTableName() {
        wait.until(ExpectedConditions.visibilityOf(tableName))
        tableName!!.click()
    }

    fun clickOnLogOutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton))
        logoutButton!!.click()
    }

    fun clickOnAddContacts() {
        wait.until(ExpectedConditions.visibilityOf(addContact))
        addContact!!.click()
    }
}
