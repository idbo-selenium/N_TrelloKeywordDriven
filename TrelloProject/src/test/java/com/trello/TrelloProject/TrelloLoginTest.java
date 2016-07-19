package com.trello.TrelloProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrelloLoginTest {

	public WebDriver driver;	
	public int seconds = 2000;
	
	public WebElement WaitforElement(By locator,int seconds){
		return(new WebDriverWait(driver,seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}	
		
	public void Openbrowser(String s, String d)	{		
		driver=new FirefoxDriver();		
	}	
	
	public void Navigate_to(String s, String d){
		driver.navigate().to("https://trello.com/login");
	}	
	
	public void Waitfor(String s, String d) throws InterruptedException{
		Thread.sleep(5000);
	}
	
	public void Enterusername(String s,String d){
		WaitforElement(By.id(s), seconds).sendKeys(d);
	}
	
	public void Enterpassword(String s,String d){
		WaitforElement(By.id(s), seconds).sendKeys(d);
	}
	
	public void Clicksignin(String s,String d){
		WaitforElement(By.id(s), seconds).click();
	}
	
	public void Clickprofile(String s,String d){
		WaitforElement(By.xpath(s), 5000).click();
	}
	
	public void Clicklogout(String s,String d){
		WaitforElement(By.xpath(s), seconds).click();
	}
	
	public void CloseBrowser(String s,String d){
		driver.close();
	}	
}