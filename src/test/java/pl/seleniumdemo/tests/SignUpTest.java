package pl.seleniumdemo.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest()  {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);

        hotelSearchPage.openSignUp();
        String lastname="Woźniak"; int randomNumber=(int)(Math.random()*1000);
        String email="tadeo"+randomNumber+"tester@gmail.com";
        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.setName("Tadeusz",lastname);
        signUpPage.setContact(email,"792159500");
        signUpPage.setPassword("test123");
        signUpPage.setSubmitBtn();

        LoggedUserPage loggedUserPage=new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Tadeusz Woźniak");


    }
    @Test
    public void signUpTest2()  {
        String lastname="Woźniak"; int randomNumber=(int)(Math.random()*1000);
        String email="tadeo"+randomNumber+"tester@gmail.com";
        User user=new User();
        user.setFirstName("Tadeusz");
        user.setLastName(lastname);
        user.setPhone("792159500");
        user.setEmail(email);
        user.setPassword("test123");


        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUp();

        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.fillingSignUpForm(user);

        LoggedUserPage loggedUserPage=new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Tadeusz Woźniak");


    }
    @Test
    public void signUpwithoutform() {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUp();
        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.setSubmitBtn();


        Assert.assertTrue(signUpPage.alertisDisplayed());
        List<String> errors= signUpPage.getErrors();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();


    }
    @Test
    public void signWithFailEmail(){

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUp();
        String lastname="Woźniak"; int randomNumber=(int)(Math.random()*1000);
        String email="tadeo"+randomNumber+"tester@gmail.com";
        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.setName("Tadeusz",lastname);
        signUpPage.setContact("tester.pl","792159500");
        signUpPage.setPassword("test123");
        signUpPage.setSubmitBtn();
        WebElement emailAlert= signUpPage.emailAlert;
        Assert.assertTrue(emailAlert.isDisplayed());


    }


    }
