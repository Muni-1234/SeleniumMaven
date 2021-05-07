package com.nopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class orangehrm_Old {

	WebDriver driver;

	@BeforeTest
	public void Browser_Launch() {
		//System. setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		Assert.assertEquals(actualTitle,expectedTitle);
	}

	@Test
	public void Login() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		Thread.sleep(1000);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(1000);
	}
	
	@Test
	public void Logout() throws InterruptedException {
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		Assert.assertEquals(actualTitle,expectedTitle);
		
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	@AfterTest
	public void Browser_Close() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		Thread.sleep(2000);
	}

}
