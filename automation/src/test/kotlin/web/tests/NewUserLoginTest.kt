package web.tests

import api.controller.UserController
import api.dataobject.UserRequestBody
import org.testng.Assert.*
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.helperClass.HelperClassFunctions
import web.page.ContactListHomePage
import web.page.ContactListLoginPage

class NewUserLoginTest: TestBase() {

    private lateinit var contactListLoginPage: ContactListLoginPage
    private lateinit var contactListHomePage: ContactListHomePage
    private lateinit var helperClassFunctions: HelperClassFunctions
    private val password = "Test123!"
    private lateinit var email: String

    @BeforeClass
    fun initPageObjects() {
        contactListLoginPage = ContactListLoginPage(driver)
        contactListHomePage = ContactListHomePage(driver)
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun newUserLoginTest() {
        driver.get(helperClassFunctions.baseURL)
        email = helperClassFunctions.generateTestEmail()
        val userController = UserController()
        val userRequestBody = UserRequestBody(
            firstName = "Test",
            lastName = "User",
            email = email,
            password = password
        )
        val response = userController.createUser(userRequestBody)
        assertNotNull(response, "User creation failed!")

        val contactListLoginPage = contactListLoginPage
        contactListLoginPage.fillEmailField(email)
        contactListLoginPage.fillPasswordField(password)
        contactListLoginPage.clickOnSubmitButton()
        val contactListHomePage = contactListHomePage
        assertTrue(contactListHomePage.isLogOutButtonDisplayed)
        contactListHomePage.clickOnLogOutButton()
        assertTrue(contactListLoginPage.isSubmitButtonDisplayed)
    }
}