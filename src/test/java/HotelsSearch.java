import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelsSearch {
    @Test
    public void searchHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("26/12/2021");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream().filter(WebElement::isDisplayed)//el ->el.isDisplayed()
                .findFirst().ifPresent(WebElement::click);//el ->el.click()
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        //driver.findElement(By.id("childPlusBtn")).click();
        Actions actions=new Actions(driver);
        WebElement button=driver.findElement(By.id("childPlusBtn"));
        actions.doubleClick(button).perform();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream().map(el->el.getAttribute("textContent")).collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(System.out::println);//el-> System.out.println(el)
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }
}
