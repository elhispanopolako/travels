package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;

import java.util.List;

public class HotelsSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("22/04/2022", "26/04/2022");
        hotelSearchPage.setTravellers(2, 2);
        hotelSearchPage.performSearch();

      /*  driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream().filter(WebElement::isDisplayed)//el ->el.isDisplayed()
                .findFirst().ifPresent(WebElement::click);//el ->el.click()
        Actions actions=new Actions(driver);
        WebElement button=driver.findElement(By.id("childPlusBtn"));
        actions.doubleClick(button).perform();*/
        ResultPage resultPage = new ResultPage(driver);

        List<String> hotelNames = resultPage.getHotelsNames();
        System.out.println(hotelNames.size());
        hotelNames.forEach(System.out::println);//el-> System.out.println(el)
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }

    @Test
    public void searchHotelTestFluent() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver)
                .setCity("Dubai")
                .setDates("22/04/2022", "26/04/2022")
                .setTravellers(2, 2);
        List<String> hotelNames = hotelSearchPage.performSearch().getHotelsNames();
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }

    @Test
    public void searchZadtest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("16/04/2022", "22/04/2022");
        hotelSearchPage.setTravellers(0, 1);
        hotelSearchPage.performSearch();

        ResultPage resultPage = new ResultPage(driver);

        Assert.assertEquals(resultPage.noHotelGetText(), "No Results Found");
        Assert.assertTrue(resultPage.noHotelResult.isDisplayed());
    }

    @Test
    public void searchFluentTest() {
        ResultPage resultPage = new HotelSearchPage(driver)
                .setDates("16/04/2022", "22/04/2022")
                .setTravellers(0, 1)
                .performSearch();

        Assert.assertEquals(resultPage.noHotelGetText(), "No Results Found");
        Assert.assertTrue(resultPage.noHotelResult.isDisplayed());


    }
}
