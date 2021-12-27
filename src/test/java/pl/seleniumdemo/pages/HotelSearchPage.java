package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {
    @FindBy(xpath ="//span[text()='Search by Hotel or City Name']" )
    private WebElement searchHotelSpan;
    @FindBy(xpath ="//div[@id='select2-drop']//input" )
    private WebElement searchHotelInput;
    @FindBy(xpath ="//span[@class='select2-match' and text()='Dubai']" )
    private WebElement hotelMatch;
    @FindBy(name ="checkin" )
    private WebElement checkInInput;
    @FindBy(name ="checkout" )
    private WebElement checkOutInput;
    @FindBy(id ="travellersInput" )
    private WebElement travellersInput;
    @FindBy(id ="adultPlusBtn" )
    private WebElement adultPlusInput;
    @FindBy(id ="childPlusBtn" )
    private WebElement childPlusInput;
    @FindBy(xpath ="//button[text()=' Search']" )
    private WebElement searchButton;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void setCity(String cityName){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }
    public void setDates(String checkin,String checkout){
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
    }
    public void setTravellers(){
        travellersInput.click();
        adultPlusInput.click();
        childPlusInput.click();
    }
    public void performSearch(){
        searchButton.click();
    }
}
