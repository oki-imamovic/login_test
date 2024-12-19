package web.tests

import api.controller.AdminUserList
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.helperClass.HelperClassFunctions
import web.page.ContactListHomePage
import web.page.ContactListLoginPage

class NewAdminUserBodyLoginTest: TestBase() {

    private lateinit var contactListLoginPage: ContactListLoginPage
    private lateinit var contactListHomePage: ContactListHomePage
    private lateinit var helperClassFunctions: HelperClassFunctions
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private var token: String = "token"

    @BeforeClass
    fun initPageObjects() {
        contactListLoginPage = ContactListLoginPage(driver)
        contactListHomePage = ContactListHomePage(driver)
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun newUserLoginTest() {
        driver.get(helperClassFunctions.baseURL)
        firstName = helperClassFunctions.generateUserName()
        lastName = helperClassFunctions.generateUserName()
        email = helperClassFunctions.generateTestEmail()
        password = helperClassFunctions.generateTestPassword()
        val adminUserList = AdminUserList()
        val response = adminUserList.createUser(firstName, lastName, email, password)
        assertNotNull(response, "ERROR: User creation failed!")

        val contactListLoginPage = contactListLoginPage
        contactListLoginPage.fillEmailField(email)
        contactListLoginPage.fillPasswordField(password)
        contactListLoginPage.clickOnSubmitButton()
        val contactListHomePage = contactListHomePage
        assertTrue(contactListHomePage.isLogOutButtonDisplayed)
        contactListHomePage.clickOnLogOutButton()
        assertTrue(contactListLoginPage.isSignUpButtonDisplayed)
    }
}