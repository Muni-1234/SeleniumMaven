package com.nopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class nopCommerce {

	WebDriver driver;

	@BeforeTest
	public void Browser_Launch() {
		//System. setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Your store. Login";
		Assert.assertEquals(actualTitle,expectedTitle);
	}

	@Test
	public void Login() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
		Thread.sleep(1000);
	}

	@Test
	public void Logout() throws InterruptedException {
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";
		Assert.assertEquals(actualTitle,expectedTitle);
		
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);
	}

	@AfterTest
	public void Browser_Close() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
