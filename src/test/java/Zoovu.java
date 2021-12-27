import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Zoovu {
   @Test
    public void googleSearch(){
       WebDriverManager.chromedriver().setup();
       WebDriver driver=new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
       driver.get("https://www.google.com/");
       driver.findElement(By.id("L2AGLb")).click();
      WebElement searchField= driver.findElement(By.name("q"));
      searchField.sendKeys("Zoovu");
      searchField.sendKeys(Keys.ENTER);
      WebElement result= driver.findElement(By.xpath("//a[@href='https://zoovu.com/']"));
      Assert.assertTrue(result.isDisplayed());
      driver.quit();
   }
}
