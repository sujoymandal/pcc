package tests;

import org.testng.Assert;

import base.TestClass;
import pages.HomePage;
import pages.LoginPage;

public class UpdateBill extends TestClass {

	@Override
	public void execute() {
		Boolean flag = false;
		LoginPage log = new LoginPage();
		HomePage home = new HomePage();
		log.loginApplication();
		flag = home.gotoOperationalMenu();
		flag = home.gotoDocumentationMenu();
		flag=home.gotoBLsearchMenu();
		flag=home.gotoBillLadingWindow();
		flag=home.selectBLno();
		flag=home.enterBillNo();
		flag=home.clickOnSearch();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=home.clickOnGridAction();
		flag=home.clickOnBLInnerMenu();
		flag=home.gotoUpdateWindow();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=home.enterMarksAndNumbers();
		flag=home.enterGoodsDescription();
		flag=home.clickOnSave();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=home.acceptWarning();
		flag=home.logOut();
		
		Assert.assertTrue(flag);

	}

}
