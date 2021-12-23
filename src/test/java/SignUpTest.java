import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {
    @Test
    public void signUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        String lastname="Woźniak"; int randomNumber=(int)(Math.random()*1000);
        String email="tadeo"+randomNumber+"tester@gmail.com";

        driver.findElement(By.name("firstname")).sendKeys("Tadeusz");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("792159500");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElements(By.xpath("//button[@type='submit']")).get(0).click();

        WebElement header= driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(header.getText().contains(lastname));
        Assert.assertEquals(header.getText(), "Hi, Tadeusz Woźniak");

    }
    @Test
    public void signUpwithoutform() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElements(By.xpath("//button[@type='submit']")).get(0).click();
        WebElement alert= driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        Assert.assertTrue(alert.isDisplayed());
        List<String> errors= driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
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
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        String lastname="Woźniak"; int randomNumber=(int)(Math.random()*1000);
        String email="tadeo"+randomNumber+"tester@gmail.com";

        driver.findElement(By.name("firstname")).sendKeys("Tadeusz");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("792159500");
        driver.findElement(By.name("email")).sendKeys("tester.pl");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElements(By.xpath("//button[@type='submit']")).get(0).click();
        WebElement emailAlert= driver.findElement(By.xpath("//p[text()='The Email field must contain a valid email address.']"));
        Assert.assertTrue(emailAlert.isDisplayed());

    }


    }
