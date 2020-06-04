import Config.MainConf;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class TestClass extends MainConf {

    @BeforeAll
    static void beforeClass(){
        initialization();
    }

    @AfterAll
    static void afterAll(){
        driver.quit();
    }

    @Test
    public void firstTest() {
        driver.get("https://yandex.ru/");
        driver.findElement(By.xpath("//input[@aria-label=\"Запрос\"]")).sendKeys("Яндекс почта");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href=\"https://mail.yandex.ru/\"]"))).click();

        Set<String> windows = driver.getWindowHandles();
        for(String s : windows){
            driver.switchTo().window(s);
        }

        js.executeScript("scrollBy(0,2500)");

        driver.findElement(By.xpath("//a[@class=\"button2 button2_size_mail-big button2_theme_mail-grey " +
                "button2_type_link FooterButtons-Button FooterButtons-Button-Enter\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"passp-field-login\"]")).sendKeys(RandomStringUtils.randomAlphabetic(15));
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        String error = driver.findElement(By.xpath("//div[@class=\"passp-form-field__error\"]")).getText();

        Assertions.assertEquals("Такого аккаунта нет",error);
    }
}