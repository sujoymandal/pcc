package pages;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;

public class HomePage extends BaseClass {

	private By userDisp = By.xpath("//span[@id='userGreeting1']");
	private By operationalMenu = By.xpath("(//span[@class='k-link k-menu-link' and text()='Operational'])[1]");
	private By documentMenu = By.xpath("//span[text()='Documentation']");
	private By blSearchMenu=By.xpath("//li/a[text()='B/L Search']");
	private By radioBillNo=By.xpath("//input[@id='radBlNo']");
	private By textArea=By.xpath("//textarea[@id='txtBlSlNo']");
	private By searchButton=By.xpath("//input[@id='btnSearch']");
	private By gridAction=By.xpath("//div[@class='gridAction']");
	private By blInnerMenu=By.xpath("(//div[text()='Edit B/L'])[2]");
	private By marksNumbers=By.xpath("//textarea[@id='txtMksNos']");
	private By goodsDescription=By.xpath("//textarea[@id='txtPkgDscr']");
	private By saveButton=By.xpath("//input[@id='btnSave']");
	private By yesButton=By.xpath("//input[@id='uxMsg-button1']");
	private By logout=By.xpath("//a[text()='Logout']");

	private String searchWindowTitle="MOLJTY - Bill of Lading - Search (OPBLW01)";
	private String updateWindowTitle="MOLJTY - Create/Modify Bill of Lading(OPBLW03)"; 
			
	
	public boolean isLoggedIn(){
		boolean flag=false;
		test.log(LogStatus.INFO, "isLoggedIn Started..");
		flag=isElementDisplayed(userDisp);
		test.log(LogStatus.INFO, "isLoggedIn ended..");
		return flag;
	}
	
	public boolean gotoOperationalMenu(){
		boolean flag=false;
		test.log(LogStatus.INFO, "gotoOperationalMenu Started..");
		flag=moveToElement(operationalMenu);
		test.log(LogStatus.INFO, "gotoOperationalMenu ended..");
		return flag;
	}
	
	public boolean gotoDocumentationMenu(){
		boolean flag=false;
		test.log(LogStatus.INFO, "gotoDocumentationMenu Started..");
		flag=moveToElement(documentMenu);
		test.log(LogStatus.INFO, "gotoDocumentationMenu ended..");
		return flag;
	}
	
	public boolean gotoBLsearchMenu(){
		boolean flag=false;
		test.log(LogStatus.INFO, "gotoBLsearchMenu Started..");
		flag=clickElement(blSearchMenu);
		test.log(LogStatus.INFO, "gotoBLsearchMenu ended..");
		return flag;
	}
	
	public boolean gotoBillLadingWindow(){
		boolean flag=false;
		test.log(LogStatus.INFO, "gotoBillLadingWindow Started..");
		flag=switchToWindow(searchWindowTitle);
		test.log(LogStatus.INFO, "gotoBillLadingWindow ended..");
		return flag;
	}
	
	
	public boolean selectBLno(){
		boolean flag=false;
		test.log(LogStatus.INFO, "selectBLno Started..");
		flag=clickElement(radioBillNo);
		test.log(LogStatus.INFO, "selectBLno ended..");
		return flag;
	}
	
	public boolean enterBillNo(){
		boolean flag=false;
		test.log(LogStatus.INFO, "enterBillNo Started..");
		flag=enterText(textArea, "18000047390");
		test.log(LogStatus.INFO, "enterBillNo ended..");
		return flag;
	}
	
	public boolean clickOnSearch(){
		boolean flag=false;
		test.log(LogStatus.INFO, "clickOnSearch Started..");
		flag=clickElement(searchButton);
		test.log(LogStatus.INFO, "clickOnSearch ended..");
		return flag;
	}
	
	public boolean clickOnGridAction(){
		boolean flag=false;
		test.log(LogStatus.INFO, "clickOnGridAction Started..");
		flag=clickElement(gridAction);
		test.log(LogStatus.INFO, "clickOnGridAction ended..");
		return flag;
	}
	
	public boolean clickOnBLInnerMenu(){
		boolean flag=false;
		test.log(LogStatus.INFO, "clickOnBLInnerMenu Started..");
		flag=clickElement(blInnerMenu);
		test.log(LogStatus.INFO, "clickOnBLInnerMenu ended..");
		return flag;
	}
	
	public boolean gotoUpdateWindow(){
		boolean flag=false;
		test.log(LogStatus.INFO, "gotoUpdateWindow Started..");
		flag=switchToWindow(updateWindowTitle);
		test.log(LogStatus.INFO, "gotoUpdateWindow ended..");
		return flag;
	}

	
	public boolean enterMarksAndNumbers(){
		boolean flag=false;
		test.log(LogStatus.INFO, "enterMarksAndNumbers Started..");
		flag=enterText(marksNumbers, "test data");
		test.log(LogStatus.INFO, "enterMarksAndNumbers ended..");
		return flag;
	}
	
	public boolean enterGoodsDescription(){
		boolean flag=false;
		test.log(LogStatus.INFO, "enterGoodsDescription Started..");
		flag=clearAndEnterText(goodsDescription, "test data");
		test.log(LogStatus.INFO, "enterGoodsDescription ended..");
		return flag;
	}
	
	public boolean clickOnSave(){
		boolean flag=false;
		test.log(LogStatus.INFO, "clickOnSave Started..");
		flag=clickElement(saveButton);
		test.log(LogStatus.INFO, "clickOnSave ended..");
		return flag;
	}
	
	public boolean acceptWarning(){
		boolean flag=false;
		test.log(LogStatus.INFO, "acceptWarning Started..");
		flag=clickElement(yesButton);
		test.log(LogStatus.INFO, "acceptWarning ended..");
		return flag;
	}
	
	public boolean logOut(){
		
		boolean flag=false;
		test.log(LogStatus.INFO, "logOut Started..");
		flag=clickElement(logout);
		test.log(LogStatus.INFO, "logOut ended..");
		return flag;
		
	}

}
