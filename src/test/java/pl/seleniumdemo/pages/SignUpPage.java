package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
      @FindBy(name ="firstname" )
       private WebElement firstNameLink;
    @FindBy(name ="lastname" )
    private WebElement lastNameLink;
    @FindBy(name ="phone" )
    private WebElement phoneLink;
    @FindBy(name ="email" )
    private WebElement emailLink;
    @FindBy(name ="password" )
    private WebElement passwordLink;
    @FindBy(name ="confirmpassword" )
    private WebElement confirmpasswordLink;
    @FindBy(xpath ="//button[@type='submit']" )
    private List<WebElement> submitBtn;
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement alert;
    @FindBy(xpath = "//p[text()='The Email field must contain a valid email address.']")
    public WebElement emailAlert;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void setName(String firstName,String lastName){
        firstNameLink.sendKeys(firstName);
        lastNameLink.sendKeys(lastName);
    }
    public void setContact(String email,String phone){
        phoneLink.sendKeys(phone);
        emailLink.sendKeys(email);
    }

    public void setPassword(String password){
        passwordLink.sendKeys(password);
        confirmpasswordLink.sendKeys(password);
    }
    public void setSubmitBtn(){
        submitBtn.get(0).click();
    }
    public List<String> getErrors(){
       return  errors.stream()
                .map(WebElement::getText)
               .collect(Collectors.toList());
    }
    public Boolean alertisDisplayed(){
       return alert.isDisplayed();
    }
    public void fillingSignUpForm(User user){
        firstNameLink.sendKeys(user.getFirstName());
        lastNameLink.sendKeys(user.getLastName());
        phoneLink.sendKeys(user.getPhone());
        emailLink.sendKeys(user.getEmail());
        setPassword(user.getPassword());
        setSubmitBtn();
    }

}
