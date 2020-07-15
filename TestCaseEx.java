import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCaseEx {
    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\MySurface\\Desktop\\Selenium\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://ebfs.bruteforcesolution.net/ebfs/index.php");


    }
    @AfterMethod
    public void CloseBrowser(){

        driver.close();
    }

    @Test(priority=0,enabled = true)
    public void TestCase01(){
    driver.findElement(By.xpath("//img[@class='logo img-responsive']"));


    }
    //Validate SignIn Button
    @Test(priority = 1,enabled = true)
    public void TestCase02(){
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String signInPageTitle = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        System.out.println("SignIn Page Title: " + signInPageTitle);
        Assert.assertEquals(signInPageTitle, "AUTHENTICATION");

        
    }
    //Login with Valid ID&Pass
    @Test(priority = 2,enabled = true)
    public void TestCase03(){
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("iku911@live.com");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("12345678");
        driver.findElement(By.xpath("//p[@class='submit']//span[1]")).click();
        String  signinMyAccount = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        System.out.println("LogIn to MyAccount:" + signinMyAccount);
        Assert.assertEquals(signinMyAccount,"MY ACCOUNT");


    }
    //LogIn with invalid ID or Password
    @Test(priority = 3,enabled = true)
    public void Testcase04(){
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@live.com");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("12345678");
        driver.findElement(By.xpath("//p[@class='submit']//span[1]")).click();
        String  signinFaild = driver.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]")).getText();
        System.out.println("LogIn to MyAccount:" + signinFaild);
        Assert.assertEquals(signinFaild,"Authentication failed.");

    }
    //Create an Account
    @Test(priority = 4)
    public void TestCase05(){
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("iku911@hmail.com");
        driver.findElement(By.xpath("//form[@id='create-account_form']//span[1]")).click();
        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("MD");
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("iQbal");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("iQbal123");
        Select days = new Select(driver.findElement(By.xpath("//select[@id='days']")));
        days.selectByVisibleText("10  ");
        Select months = new Select(driver.findElement(By.xpath("//select[@id='months']")));
        months.selectByVisibleText("June ");
        Select years = new Select(driver.findElement(By.xpath("//select[@id='years']")));
        years.selectByVisibleText("2000  ");

        driver.findElement(By.xpath("//input[@id='newsletter']")).click();
        driver.findElement(By.xpath("//input[@id='optin']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();

        String AccountCreated = driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        System.out.println("Account Created Successful"+ AccountCreated);
        Assert.assertEquals(AccountCreated,"MY ACCOUNT");

        @Test (priority = 5,enabled = false)
        public void testcase05(){


        }




    }
}
