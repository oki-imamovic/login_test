package web.tests

import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.helperClass.HelperClassFunctions
import web.page.ContactListHomePage
import web.page.ContactListLoginPage

class ExistingUserLogInTest: TestBase(){

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
        assertTrue(contactListHomePage.isLogOutButtonDisplayed)
        assertTrue(contactListHomePage.isNamePresentInTableList("Hasim Turcin"))
        assertTrue(contactListHomePage.isTableNameListEmpty)
        contactListHomePage.clickOnLogOutButton()
        assertTrue(contactListLoginPage.isSubmitButtonDisplayed)
    }
}
