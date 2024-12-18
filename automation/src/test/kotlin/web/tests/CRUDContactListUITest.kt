package web.tests

import org.openqa.selenium.Alert
import org.testng.Assert.*
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.TestBase
import web.helperClass.HelperClassFunctions
import web.page.*

class CRUDContactListUITest: TestBase() {

    private lateinit var contactListLoginPage: ContactListLoginPage
    private lateinit var contactListHomePage: ContactListHomePage
    private lateinit var userSignUpPage: UserSignUpPage
    private lateinit var addContactPage: AddContactPage
    private lateinit var contactDetailsPage: ContactDetailsPage
    private lateinit var helperClassFunctions: HelperClassFunctions
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var newFirstName: String
    private lateinit var newLastName: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String

    @BeforeClass
    fun initPageObjects() {
        contactListLoginPage = ContactListLoginPage(driver)
        contactListHomePage = ContactListHomePage(driver)
        userSignUpPage = UserSignUpPage(driver)
        addContactPage = AddContactPage(driver)
        contactDetailsPage = ContactDetailsPage(driver)
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun cRUDContactUserUITest() {
        driver.get(helperClassFunctions.baseURL)
        firstName = helperClassFunctions.generateUserName()
        lastName = helperClassFunctions.generateUserName()
        newFirstName = helperClassFunctions.generateUserName()
        newLastName = helperClassFunctions.generateUserName()
        email = helperClassFunctions.generateTestEmail()
        phone = helperClassFunctions.generatePhoneNumber()
        password = helperClassFunctions.generateTestPassword()

        val contactListLoginPage = contactListLoginPage
        contactListLoginPage.clickOnSignUpButton()
        val contactSignUpPage = userSignUpPage
        contactSignUpPage.fillFirstName(firstName)
        contactSignUpPage.fillLastNameInput(lastName)
        contactSignUpPage.fillEmailInput(email)
        contactSignUpPage.fillPasswordInput(password)
        contactSignUpPage.clickOnSubmitButton()
        val contactListHomePage = contactListHomePage
        assertTrue(contactListHomePage.isLogOutButtonDisplayed, "ERROR: User Failed to SignUp")
        contactListHomePage.clickOnAddContacts()
        val addContactPage = addContactPage
        addContactPage.fillFirstNameInput(firstName)
        addContactPage.fillLastNameInput(lastName)
        addContactPage.fillDOBInput(DOB)
        addContactPage.fillEmailInput(email)
        addContactPage.fillPhoneInput(phone)
        addContactPage.fillStreetInput(STREET)
        addContactPage.fillCityInput(CITY)
        addContactPage.fillProvinceInput(MAGIC)
        addContactPage.fillPostalCodeInput(POSTAL_CODE)
        addContactPage.fillCountryInput(COUNTRY)
        addContactPage.clickOnSubmitButton()
        assertEquals(contactListHomePage.isNamePresentInTableList, "$firstName $lastName", "ERROR: First & Last Name is not matching")
        assertEquals(contactListHomePage.getTableDOBText, DOB, "ERROR: First Name is not matching")
        assertEquals(contactListHomePage.getTableEmailText, email, "ERROR: Email is not matching")
        assertEquals(contactListHomePage.getTPhoneText, phone, "ERROR: Phone number is not matching")
        assertEquals(contactListHomePage.getTAddressText, STREET, "ERROR: Address Name is not matching")
        assertEquals(contactListHomePage.getTCityText, "$CITY $MAGIC $POSTAL_CODE", "ERROR: City, Street & Postal Code not matching")
        assertEquals(contactListHomePage.getTCountryText, COUNTRY, "ERROR: Country Name is not matching")
        contactListHomePage.clickOnTableName()
        val contactDetailsPage = contactDetailsPage
        contactDetailsPage.clickOnEditContactButton()
        addContactPage.fillFirstNameInput(newFirstName)
        addContactPage.fillLastNameInput(newLastName)
        addContactPage.clickOnSubmitButton()
        contactDetailsPage.clickOnReturnButton()
        assertEquals(contactListHomePage.isNamePresentInTableList, "$newFirstName $newLastName", "ERROR: First & Last Name is not matching")
        assertEquals(contactListHomePage.getTableDOBText, DOB, "ERROR: First Name is not matching")
        assertEquals(contactListHomePage.getTableEmailText, email, "ERROR: Email is not matching")
        assertEquals(contactListHomePage.getTPhoneText, phone, "ERROR: Phone number is not matching")
        assertEquals(contactListHomePage.getTAddressText, STREET, "ERROR: Address Name is not matching")
        assertEquals(contactListHomePage.getTCityText, "$CITY $MAGIC $POSTAL_CODE", "ERROR: City, Street & Postal Code not matching")
        assertEquals(contactListHomePage.getTCountryText, COUNTRY, "ERROR: Country Name is not matching")
        contactListHomePage.clickOnTableName()
        contactDetailsPage.clickOnDeleteButton()
        val alert: Alert = driver.switchTo().alert()
        alert.accept()
        contactListHomePage.clickOnAddContacts()
        addContactPage.fillFirstNameInput(firstName)
        addContactPage.fillLastNameInput(lastName)
        addContactPage.clickOnSubmitButton()
        assertNotEquals(contactListHomePage.isNamePresentInTableList, "$newFirstName $newLastName", "ERROR: First & Last Name is existing after deletion")
        assertEquals(contactListHomePage.isNamePresentInTableList, "$firstName ${lastName}", "ERROR: First & Name is not matching")
    }

    private companion object {
        const val DOB = "1999/03/12"
        const val STREET = "Magic Street"
        const val CITY = "Automation"
        const val MAGIC = "Magic"
        const val POSTAL_CODE = "11111"
        const val COUNTRY = "BiH"
    }
}