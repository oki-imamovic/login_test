package tests

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class SeleniumTest {

    private lateinit var driver: WebDriver

    @BeforeClass
    fun setup() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
    }

    @Test
    fun testGoogleHomePage() {
        driver.get("https://www.google.com")
        assert(driver.title.contains("Google")) { "Page title is incorrect!" }
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
    }
}
