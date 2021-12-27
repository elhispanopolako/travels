package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class HotelsSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("22/04/2022","26/04/2022");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

      /*  driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream().filter(WebElement::isDisplayed)//el ->el.isDisplayed()
                .findFirst().ifPresent(WebElement::click);//el ->el.click()
        Actions actions=new Actions(driver);
        WebElement button=driver.findElement(By.id("childPlusBtn"));
        actions.doubleClick(button).perform();*/

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream().map(el->el.getAttribute("textContent")).collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(System.out::println);//el-> System.out.println(el)
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }
    @Test
    public void searchZadtest() {

        driver.findElement(By.name("checkin")).sendKeys("16/04/2022");
        driver.findElement(By.name("checkout")).sendKeys("22/04/2022");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        WebElement result= driver.findElement(By.xpath("//h2[text()='No Results Found']"));
        Assert.assertEquals(result.getText(),"No Results Found");
        Assert.assertTrue(result.isDisplayed());




    }
}
