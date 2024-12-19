package web.tests

import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.helperClass.HelperClassFunctions
import web.page.ContactListHomePage
import web.page.ContactListLoginPage

class ExistingAdminUserBodyLogInTest: TestBase(){

    private lateinit var contactListLoginPage: ContactListLoginPage
    private lateinit var contactListHomePage: ContactListHomePage
    private lateinit var helperClassFunctions: HelperClassFunctions

    @BeforeClass
    fun initPageObjects() {
        contactListLoginPage = ContactListLoginPage(driver)
        contactListHomePage = ContactListHomePage(driver)
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun existingUserLogInTest() {
        driver.get(helperClassFunctions.baseURL)
        val contactListLoginPage = contactListLoginPage

        contactListLoginPage.logInWithExistingUser()
        val contactListHomePage = contactListHomePage
        assertTrue(contactListHomePage.isLogOutButtonDisplayed, "ERROR: User Did not log in")
        assertEquals(contactListHomePage.isNamePresentInTableList, "TestD231 $423", "ERROR: Expected Unique Name not present in the list")
        contactListHomePage.clickOnLogOutButton()
        assertTrue(contactListLoginPage.isSignUpButtonDisplayed, "ERROR: USER did not log out")
    }
}
