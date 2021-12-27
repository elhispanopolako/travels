package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    private List<WebElement> hotelList;
    @FindBy(xpath = "//h2[text()='No Results Found']")
    public WebElement noHotelResult;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public List<String> getHotelsNames(){
      return  hotelList.stream()
              .map(el->el.getAttribute("textContent"))
              .collect(Collectors.toList());
    }
    public String noHotelGetText(){
       return noHotelResult.getText();

    }
}
