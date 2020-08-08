package pages;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;

public class LoginPage extends BaseClass {

	private By userName = By.xpath("(//label[text()='User Name']/following::input)[1]");
	private By passwrod = By.xpath("(//label[text()='Password']/following::input)[1]");
	private By loginButton = By.xpath("(//label[text()='Password']/following::input)[2]");

	
	public void loginApplication(){
		test.log(LogStatus.INFO, "loginApplication Started..");
		enterText(userName,readProperty("User") );
		enterText(passwrod, readProperty("Password"));
		clickElement(loginButton);
		test.log(LogStatus.INFO, "loginApplication ended..");
		
	}

}
