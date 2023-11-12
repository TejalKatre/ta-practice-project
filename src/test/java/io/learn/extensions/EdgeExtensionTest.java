package io.learn.extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class EdgeExtensionTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws URISyntaxException {
        EdgeOptions options = new EdgeOptions();
        Path extension = Paths
                .get(ClassLoader.getSystemResource("dark-bg.crx").toURI());
        options.addExtensions(extension.toFile());

        driver = new EdgeDriver(options);
    }


    @Test
    void testAddExtension() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement body = driver.findElement(By.tagName("body"));
        String whiteRgba = "rgba(255, 255, 255, 1)";
        wait.until(not(attributeToBe(body, "background-color", whiteRgba)));
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        driver.quit();
    }
}
