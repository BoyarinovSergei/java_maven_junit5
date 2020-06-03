import Config.MainConf;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
    public void firstTest() throws InterruptedException {
        driver.get("https://yandex.ru/");
        driver.findElement(By.xpath("//input[@aria-label=\"Запрос\"]")).sendKeys("Яндекс почта");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        driver.findElement(By.xpath("(//div[@class=\"organic__url-text\"])[1]")).click();

        Set<String> windows = driver.getWindowHandles();
        for(String s : windows){
            driver.switchTo().window(s);
        }

        js.executeScript("scrollBy(0,2500)");

        driver.findElement(By.xpath("(//div[@class=\"FooterButtons\"]/a)[2]")).click();
        driver.findElement(By.xpath("//input[@id=\"passp-field-login\"]")).sendKeys(RandomStringUtils.randomAlphabetic(15));
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        String error = driver.findElement(By.xpath("//div[contains(text(),'Такого аккаунта нет')]")).getText();
        Assertions.assertEquals(error,"Такого аккаунта нет");
    }
}