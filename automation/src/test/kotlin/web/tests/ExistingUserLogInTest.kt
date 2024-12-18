package web.tests

import org.testng.Assert.assertTrue
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.page.ContactListHomePage
import web.page.ContactListLoginPage

class ExistingUserLogInTest: TestBase(){

    private var contactListLoginPage: ContactListLoginPage? = null
    private var contactListHomePage: ContactListHomePage? = null
    private val url = "https://thinking-tester-contact-list.herokuapp.com/"

    @BeforeClass
    fun initPageObjects() {
        contactListLoginPage = ContactListLoginPage(driver)
        contactListHomePage = ContactListHomePage(driver)
    }

    @Test
    fun existingUserLogInTest() {
        driver.get(url)
        val contactListLoginPage = contactListLoginPage!!

        contactListLoginPage.logInWithExistingUser()
        val contactListHomePage = contactListHomePage!!
        assertTrue(contactListHomePage.isLogOutButtonDisplayed)
        assertTrue(contactListHomePage.isNamePresentInTableList("Hasim Turcin"))
        assertTrue(contactListHomePage.isTableNameListEmpty)
        contactListHomePage.clickOnLogOutButton()
        assertTrue(contactListLoginPage.isSubmitButtonDisplayed)
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
    }
}
