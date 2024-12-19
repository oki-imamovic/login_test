package api.tests

import api.controller.AdminUserList
import api.controller.ContactUserList
import org.testng.Assert.*
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.helperClass.HelperClassFunctions

class CRUDContactListAPITest {

    private lateinit var helperClassFunctions: HelperClassFunctions
    private lateinit var adminFirstName: String
    private lateinit var adminLastName: String
    private lateinit var adminEmail: String
    private lateinit var adminPassword: String
    private var token: String = "token"

    @BeforeClass
    fun initPageObjects() {
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun CRUDContactListAPITest() {
        adminFirstName = helperClassFunctions.generateUserName()
        adminLastName = helperClassFunctions.generateUserName()
        adminEmail = helperClassFunctions.generateTestEmail()
        adminPassword = helperClassFunctions.generateTestPassword()

        val adminUserController = AdminUserList()
        val adminUser = adminUserController.createUser(adminFirstName, adminLastName, adminEmail, adminPassword)
        token = adminUser!!.token
        val adminLogin = adminUserController.logInUser(adminEmail, adminPassword, token)
        assertEquals(adminLogin!!.user.email, adminEmail, "ERROR: User Email not matching request, unauthorized action!!!")
        token = adminLogin.token
        val contactUserController = ContactUserList()
        val createFirsContactUser = contactUserController.addContact(helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "1999/03/04", helperClassFunctions.generateTestEmail(), helperClassFunctions.generatePhoneNumber(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "111111", helperClassFunctions.generateUserName(), token)
        val firstContactId = createFirsContactUser!!.id
        val createSecondContactUser = contactUserController.addContact(helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "1999/05/08", helperClassFunctions.generateTestEmail(), helperClassFunctions.generatePhoneNumber(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "222222", helperClassFunctions.generateUserName(), token)
        val secondContactId = createSecondContactUser!!.id
        val patchContact = contactUserController.patchContact(helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), token, firstContactId!!)
        assertEquals(patchContact!!.id, firstContactId, "ERROR: User changed the id as well")
        assertNotEquals(patchContact.firstName, createFirsContactUser.firstName, "ERROR: First name was not Patched")
        assertNotEquals(patchContact.lastName, createFirsContactUser.lastName, "ERROR: First name was not Patched")
        val updateContact = contactUserController.updateContact(helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "2002/01/04", helperClassFunctions.generateTestEmail(), helperClassFunctions.generatePhoneNumber(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), helperClassFunctions.generateUserName(), "333333", helperClassFunctions.generateUserName(), secondContactId!! ,token)
        assertEquals(updateContact!!.id, secondContactId, "ERROR: User changed the id as well")
        assertNotEquals(updateContact.firstName, createSecondContactUser.firstName, "ERROR: First name was not Patched")
        assertNotEquals(updateContact.lastName, createSecondContactUser.lastName, "ERROR: First name was not Patched")
        val deleteContact = contactUserController.deleteContactUser(token, secondContactId)
        assertEquals(deleteContact, "Contact deleted", "ERROR: Contact was not deleted")
        assertEquals(contactUserController.getContactUser(token, firstContactId)!!.id, firstContactId, "ERROR: Wrong Contact Deleted")
        assertEquals(adminUserController.deleteAdminUser(token), "200", "ERROR: User was not Deleted")
    }
}