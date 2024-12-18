package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import web.helperClass.HelperClassFunctions
import java.time.Duration

class ContactListHomePage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    private val helperClassFunctions = HelperClassFunctions()

    @FindBy(id = "logout")
    private val logoutButton: WebElement? = null

    @FindBy(xpath = "//tr[@class=\"contactTableBodyRow\"]/td[2]")
    private val tableNameList: List<WebElement>? = null

    val isLogOutButtonDisplayed: Boolean
        get() {
            wait.until(ExpectedConditions.visibilityOf(logoutButton))
            return logoutButton!!.isDisplayed
        }

    val isTableNameListEmpty: Boolean
        get() {
            wait.until(ExpectedConditions.visibilityOfAllElements(tableNameList))
            return helperClassFunctions.isListEmpty(tableNameList)
        }

    fun isNamePresentInTableList(name: String): Boolean {
        wait.until(ExpectedConditions.visibilityOfAllElements(tableNameList))
        return helperClassFunctions.isElementIncluded(name, tableNameList)
    }

    fun clickOnLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton))
        logoutButton!!.click()
    }
}
