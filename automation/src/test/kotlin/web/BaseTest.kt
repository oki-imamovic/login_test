package web

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

abstract class TestBase {

    lateinit var driver: WebDriver
        private set

    @BeforeTest
    fun setup() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
        driver.manage()?.window()?.maximize()
    }

    @AfterTest
    fun kill() {
        driver.quit()
    }
}