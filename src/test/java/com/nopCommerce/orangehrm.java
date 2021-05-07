package com.nopCommerce;

import com.nop.common.Common_Utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class orangehrm {

	WebDriver driver;
	Common_Utilities Testdata = new Common_Utilities();
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		
		String URL = Testdata.getpropertyvalue("URL", "Test_config.properties");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			//Docker container / Selenium GridHub Etc.
			/*DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "chrome");
			try {			
					driver = new RemoteWebDriver(new 
					URL("http://13.233.116.37:4444/wd/hub"),cap);						
			} catch(MalformedURLException e) {
				e.printStackTrace();
			}*/
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
			//Docker container / Selenium GridHub Etc.
			/*DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "firebox");
			try {			
					driver = new RemoteWebDriver(new 
					URL("http://13.233.116.37:4444/wd/hub"),cap);						
			} catch(MalformedURLException e) {
				e.printStackTrace();
			}*/
		}
		else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(URL);
		driver.manage().window().maximize();
		
	}
	
	@Test(priority = 1)
	public void orangehrmlogoTest() {
		boolean flag = false;
		
		flag = driver.findElement(By.xpath("//div[@id='divLogo']//img")).isDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 2)
	public void orangehrmTitleTest() {
		
		System.out.println(driver.getTitle());	
		Assert.assertEquals(driver.getTitle(),"OrangeHRM");
	}
	
	@Test(priority = 3)
	public void orangehrmLinksTest() {
		List<WebElement> hrmLinksList = driver.findElements(By.cssSelector("a"));
		hrmLinksList.forEach(ele -> System.out.println(ele.getText()));
		Assert.assertEquals(hrmLinksList.size(), 6);
	}
	
	@Test(priority = 4)
	public void Login() throws IOException {
			
		String UserName = Testdata.getpropertyvalue("UserName", "Test_config.properties");
		String Password = Testdata.getpropertyvalue("Password", "Test_config.properties");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys(UserName);
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys(Password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 5)
	public void Logout() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		System.out.println(driver.getTitle());	
		Assert.assertEquals(driver.getTitle(),"OrangeHRM");
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		//driver.findElement(By.xpath("//a[@id='welcome']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		//driver.findElement(By.xpath("//a[text()='Logout']")).sendKeys(Keys.ENTER);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		Thread.sleep(2000);
	}

}
