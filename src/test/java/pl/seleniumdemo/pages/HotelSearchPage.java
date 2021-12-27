package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {
    @FindBy(xpath ="//span[text()='Search by Hotel or City Name']" )
    private WebElement searchHotelSpan;
    @FindBy(xpath ="//div[@id='select2-drop']//input" )
    private WebElement searchHotelInput;
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
    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;



    private WebDriver driver;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    public void setCity(String cityName){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath=String.format("//span[@class='select2-match' and text()='%s']",cityName);
        driver.findElement(By.xpath(xpath)).click();

    }
    public void setDates(String checkin,String checkout){
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
    }
    public void setTravellers(int adultsToAdd,int childrenToAdd){
        travellersInput.click();
        addTravelers(adultPlusInput,adultsToAdd);
        addTravelers(childPlusInput,childrenToAdd);


    }
    public void addTravelers(WebElement travelersBtn,int travellersToAdd){
        for(int i=0;i<travellersToAdd;i++){
           travelersBtn.click();
    }
    }
    public void performSearch(){
        searchButton.click();
    }
    public void openSignUp(){
       myAccountLink.stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }
}
