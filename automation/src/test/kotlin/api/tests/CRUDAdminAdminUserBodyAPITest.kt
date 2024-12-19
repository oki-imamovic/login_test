package api.tests

import api.controller.AdminUserList
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import web.helperClass.HelperClassFunctions

class CRUDAdminAdminUserBodyAPITest {

    private lateinit var helperClassFunctions: HelperClassFunctions
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var updateFirstName: String
    private lateinit var updateLastName: String
    private lateinit var updateEmail: String
    private var token: String = "token"

    @BeforeClass
    fun initPageObjects() {
        helperClassFunctions = HelperClassFunctions()
    }

    @Test
    fun cRUDAdminUserAPITest() {
        firstName = helperClassFunctions.generateUserName()
        lastName = helperClassFunctions.generateUserName()
        email = helperClassFunctions.generateTestEmail()
        updateFirstName = helperClassFunctions.generateUserName()
        updateLastName = helperClassFunctions.generateUserName()
        updateEmail = helperClassFunctions.generateTestEmail()
        password = helperClassFunctions.generateTestPassword()
        val adminUserController = AdminUserList()
        val adminUser = adminUserController.createUser(firstName, lastName, email, password)
        token = adminUser!!.token
        val loginUser = adminUserController.logInUser(email, password, token)
        assertEquals(loginUser!!.user.email, email, "ERROR: User Email not matching request, unauthorized action!!!")
        val logOut = adminUserController.logOutUser(token)
        assertEquals(logOut, "200", "ERROR:User was not logged out")
        val newLogInUser = adminUserController.logInUser(email, password, token)
        assertEquals(newLogInUser!!.user.email, email, "ERROR: User Email not matching request, unauthorized action!!!")
        token = newLogInUser.token
        val getNewAdminUser = adminUserController.getCurrentUser(token)
        assertEquals(getNewAdminUser!!.firstName, firstName, "ERROR: User first name is not matching newly created user")
        assertEquals(getNewAdminUser.lastName, lastName, "ERROR: User first name is not matching newly created user")
        assertEquals(getNewAdminUser.email, email, "ERROR: User first name is not matching newly created user")
        val updateUser = adminUserController.updateUser(updateFirstName, updateLastName, updateEmail, password, token)
        assertEquals(updateUser!!.firstName, updateFirstName, "ERROR: User first name is not matching updated user")
        assertEquals(updateUser.lastName, updateLastName, "ERROR: User first name is not matching updated user")
        assertEquals(updateUser.email, updateEmail, "ERROR: User first name is not matching updated user")
        val getUpdatedUser = adminUserController.getCurrentUser(token)
        assertEquals(getUpdatedUser!!.firstName, updateFirstName, "ERROR: User first name is not matching updated user")
        assertEquals(getUpdatedUser.lastName, updateLastName, "ERROR: User first name is not matching updated user")
        assertEquals(getUpdatedUser.email, updateEmail, "ERROR: User first name is not matching updated user")
        val deleteUser = adminUserController.deleteAdminUser(token)
        assertEquals(deleteUser, "200", "ERROR: User was not deleted")
    }
}