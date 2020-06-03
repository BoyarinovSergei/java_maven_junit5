package Config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainConf {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static JavascriptExecutor js;

    public static void initialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30),Duration.ofMillis(100));
        js = (JavascriptExecutor)driver;
    }
}
