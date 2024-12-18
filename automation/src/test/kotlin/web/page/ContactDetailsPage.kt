package web.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class ContactDetailsPage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    @FindBy(id = "edit-contact")
    private val editContactButton: WebElement? = null

    @FindBy(id = "return")
    private val returnButton: WebElement? = null

    @FindBy(id = "delete")
    private val deleteButton: WebElement? = null

    fun clickOnEditContactButton() {
        wait.until(ExpectedConditions.visibilityOf(editContactButton))
        editContactButton!!.click()
    }

    fun clickOnDeleteButton() {
        wait.until(ExpectedConditions.visibilityOf(deleteButton))
        deleteButton!!.click()
    }

    fun clickOnReturnButton() {
        wait.until(ExpectedConditions.visibilityOf(returnButton))
        returnButton!!.click()
    }
}